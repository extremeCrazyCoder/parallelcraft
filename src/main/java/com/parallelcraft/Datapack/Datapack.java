package com.parallelcraft.Datapack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import org.apache.logging.log4j.util.TriConsumer;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Manages the currently loaded datapack
 * 
 * @author extremeCrazyCoder
 */
public class Datapack {
    public static final String DEFAULT_DATAPACK = "resource:com.parallelcraft.Datapack.Datapack::datapack";
    
    public static void forEachContent(String datapack, String path, TriConsumer<String, String, String> runWith) {
        for(String res : getDatapackListing(datapack, path)) {
            runWith.accept(datapack, path, res);
        }
    }
    
    public static void forEachJSONContent(String datapack, String path, BiConsumer<String, JSONObject> runWith) {
        forEachContent(datapack, path, (packInner, pathInner, file) -> {
            InputStream contentStream = getDatapackStream(packInner, pathInner + File.separator + file);
            JSONTokener contentTokener = new JSONTokener(contentStream);
            JSONObject contents = new JSONObject(contentTokener);
            runWith.accept(file.substring(0, file.lastIndexOf('.')), contents);
        });
    }
    
    private static InputStream getDatapackStream(String datapack, String path) {
        if(datapack.startsWith("file:")) {
            try {
                return new FileInputStream(datapack.substring(5) + File.separator + path);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException("Unable to parse Datapack", ex);
            }
        } else if(datapack.startsWith("resource:")) {
            String parted = datapack.substring(9);
            String parts[] = parted.split("::", 2);
            if(parts.length < 2) {
                throw new IllegalArgumentException("No class name found in datapack location");
            }
            try {
                return Class.forName(parts[0]).getResourceAsStream("/" + parts[1] + File.separator + path);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException("Unable to parse Datapack", ex);
            }
        }

        throw new UnsupportedOperationException("Datapack location seems unsupported");
    }
    
    private static String[] getDatapackListing(String datapack, String path) {
        if(datapack.startsWith("file:")) {
            return new File(datapack.substring(5) + File.separator + path).list();
        } else if(datapack.startsWith("resource:")) {
            String parted = datapack.substring(9);
            String parts[] = parted.split("::", 2);
            if(parts.length < 2) {
                throw new IllegalArgumentException("No class name found in datapack location");
            }
            try {
                return getResourceListing(Class.forName(parts[0]), parts[1] + File.separator + path);
            } catch (ClassNotFoundException | URISyntaxException | IOException ex) {
                throw new RuntimeException("Unable to parse Datapack", ex);
            }
        }

        throw new UnsupportedOperationException("Datapack location seems unsupported");
    }
    
    /**
     * List directory contents for a resource folder. Not recursive.
     * This is basically a brute-force implementation.
     * Works for regular files and also JARs.
     * copied from http://www.uofr.net/~greg/java/get-resource-listing.html
     * 
     * @author Greg Briggs
     * @param clazz Any java class that lives in the same place as the resources you want.
     * @param path Should end with "/", but not start with one.
     * @return Just the name of each member item, not the full paths.
     * @throws URISyntaxException 
     * @throws IOException 
     */
    private static String[] getResourceListing(Class clazz, String path) throws URISyntaxException, IOException {
        URL dirURL = clazz.getClassLoader().getResource(path);
        if (dirURL != null && dirURL.getProtocol().equals("file")) {
            /* A file path: easy enough */
            return new File(dirURL.toURI()).list();
        } 

        if (dirURL == null) {
            /* 
             * In case of a jar file, we can't actually find a directory.
             * Have to assume the same jar as clazz.
             */
            String me = clazz.getName().replace(".", "/")+".class";
            dirURL = clazz.getClassLoader().getResource(me);
        }

        if (dirURL.getProtocol().equals("jar")) {
            /* A JAR path */
            String jarPath = dirURL.getPath().substring(5, dirURL.getPath().indexOf("!")); //strip out only the JAR file
            JarFile jar = new JarFile(URLDecoder.decode(jarPath, "UTF-8"));
            Enumeration<JarEntry> entries = jar.entries(); //gives ALL entries in jar
            Set<String> result = new HashSet<String>(); //avoid duplicates in case it is a subdirectory
            while(entries.hasMoreElements()) {
                String name = entries.nextElement().getName();
                if (name.startsWith(path)) { //filter according to the path
                    String entry = name.substring(path.length());
                    int checkSubdir = entry.indexOf("/");
                    if (checkSubdir >= 0) {
                        // if it is a subdirectory, we just return the directory name
                        entry = entry.substring(0, checkSubdir);
                    }
                    result.add(entry);
                }
            }
            return result.toArray(new String[result.size()]);
        }

        throw new UnsupportedOperationException("Cannot list files for URL "+dirURL);
    }
}
