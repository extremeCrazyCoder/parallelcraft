
package com.parallelcraft.Networking;

import com.parallelcraft.Networking.Packets.*;
import com.parallelcraft.util.AdvancedList;
import com.parallelcraft.util.AdvancedMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 *
 * @author extremeCrazyCoder
 */
public class Protocol {
    public static final byte HANDSHAKE = -1;
    public static final byte PLAY = 0;
    public static final byte STATUS = 1;
    public static final byte LOGIN = 2;
    
    /**
     * Map of protocol type
     *  -> List of possible Packets
     */
    private static final Map<Byte, List<Supplier<? extends PacketIncoming>>> protocolIncoming;
    private static final Map<Byte, List<Class<? extends PacketOutgoing>>> protocolOutgoing;
    private static final Map<Byte, Map<Class<? extends PacketOutgoing>, Byte>> protocolOutgoingMap;
    
    static {
        protocolIncoming = new AdvancedMap<>();
        protocolOutgoing = new AdvancedMap<>();
        
        //Handshake
        protocolIncoming.put(HANDSHAKE, new AdvancedList<Supplier<? extends PacketIncoming>>()
                .append(PacketHandshakingInSetProtocol::new)
        );
        
        
        //Play
        protocolOutgoing.put(PLAY, new AdvancedList<Class<? extends PacketOutgoing>>()
            .append(PacketPlayOutSpawnEntity.class)
            .append(PacketPlayOutSpawnEntityExperienceOrb.class)
            .append(PacketPlayOutSpawnEntityLiving.class)
            .append(PacketPlayOutSpawnEntityPainting.class)
            .append(PacketPlayOutNamedEntitySpawn.class)
            .append(PacketPlayOutAddVibrationSignalPacket.class)
            .append(PacketPlayOutAnimation.class)
            .append(PacketPlayOutStatistic.class)
            .append(PacketPlayOutBlockBreak.class)
            .append(PacketPlayOutBlockBreakAnimation.class)
            .append(PacketPlayOutTileEntityData.class)
            .append(PacketPlayOutBlockAction.class)
            .append(PacketPlayOutBlockChange.class)
            .append(PacketPlayOutBoss.class)
            .append(PacketPlayOutServerDifficulty.class)
            .append(PacketPlayOutChat.class)
            .append(PacketPlayClearTitlesPacket.class)
            .append(PacketPlayOutTabComplete.class)
            .append(PacketPlayOutCommands.class)
            .append(PacketPlayOutCloseWindow.class)
            .append(PacketPlayOutWindowItems.class)
            .append(PacketPlayOutWindowData.class)
            .append(PacketPlayOutSetSlot.class)
            .append(PacketPlayOutSetCooldown.class)
            .append(PacketPlayOutCustomPayload.class)
            .append(PacketPlayOutCustomSoundEffect.class)
            .append(PacketPlayOutKickDisconnect.class)
            .append(PacketPlayOutEntityStatus.class)
            .append(PacketPlayOutExplosion.class)
            .append(PacketPlayOutUnloadChunk.class)
            .append(PacketPlayOutGameStateChange.class)
            .append(PacketPlayOutOpenWindowHorse.class)
            .append(PacketPlayInitializeBorderPacket.class)
            .append(PacketPlayOutKeepAlive.class)
            .append(PacketPlayOutMapChunk.class)
            .append(PacketPlayOutWorldEvent.class)
            .append(PacketPlayOutWorldParticles.class)
            .append(PacketPlayOutLightUpdate.class)
            .append(PacketPlayOutLogin.class)
            .append(PacketPlayOutMap.class)
            .append(PacketPlayOutOpenWindowMerchant.class)
            .append(PacketPlayOutRelEntityMove.class)
            .append(PacketPlayOutRelEntityMoveLook.class)
            .append(PacketPlayOutEntityLook.class)
            .append(PacketPlayOutVehicleMove.class)
            .append(PacketPlayOutOpenBook.class)
            .append(PacketPlayOutOpenWindow.class)
            .append(PacketPlayOutOpenSignEditor.class)
            .append(PacketPlayOutPing.class)
            .append(PacketPlayOutAutoRecipe.class)
            .append(PacketPlayOutAbilities.class)
            .append(PacketPlayOutPlayerCombatEnd.class)
            .append(PacketPlayOutPlayerCombatEnter.class)
            .append(PacketPlayOutPlayerCombatKill.class)
            .append(PacketPlayOutPlayerInfo.class)
            .append(PacketPlayOutLookAt.class)
            .append(PacketPlayOutPosition.class)
            .append(PacketPlayOutRecipes.class)
            .append(PacketPlayOutEntityDestroy.class)
            .append(PacketPlayOutRemoveEntityEffect.class)
            .append(PacketPlayOutResourcePackSend.class)
            .append(PacketPlayOutRespawn.class)
            .append(PacketPlayOutEntityHeadRotation.class)
            .append(PacketPlayOutMultiBlockChange.class)
            .append(PacketPlayOutSelectAdvancementTab.class)
            .append(PacketPlayOutSetActionBarTextPacket.class)
            .append(PacketPlayOutSetBorderCenterPacket.class)
            .append(PacketPlayOutSetBorderLerpSizePacket.class)
            .append(PacketPlayOutSetBorderSizePacket.class)
            .append(PacketPlayOutSetBorderWarningDelayPacket.class)
            .append(PacketPlayOutSetBorderWarningDistancePacket.class)
            .append(PacketPlayOutCamera.class)
            .append(PacketPlayOutHeldItemSlot.class)
            .append(PacketPlayOutViewCentre.class)
            .append(PacketPlayOutViewDistance.class)
            .append(PacketPlayOutSpawnPosition.class)
            .append(PacketPlayOutScoreboardDisplayObjective.class)
            .append(PacketPlayOutEntityMetadata.class)
            .append(PacketPlayOutAttachEntity.class)
            .append(PacketPlayOutEntityVelocity.class)
            .append(PacketPlayOutEntityEquipment.class)
            .append(PacketPlayOutExperience.class)
            .append(PacketPlayOutUpdateHealth.class)
            .append(PacketPlayOutScoreboardObjective.class)
            .append(PacketPlayOutMount.class)
            .append(PacketPlayOutScoreboardTeam.class)
            .append(PacketPlayOutScoreboardScore.class)
            .append(PacketPlayOutSetSubtitleTextPacket.class)
            .append(PacketPlayOutUpdateTime.class)
            .append(PacketPlayOutSetTitleTextPacket.class)
            .append(PacketPlayOutSetTitlesAnimationPacket.class)
            .append(PacketPlayOutTitle.class)
            .append(PacketPlayOutEntitySound.class)
            .append(PacketPlayOutNamedSoundEffect.class)
            .append(PacketPlayOutStopSound.class)
            .append(PacketPlayOutPlayerListHeaderFooter.class)
            .append(PacketPlayOutNBTQuery.class)
            .append(PacketPlayOutCollect.class)
            .append(PacketPlayOutEntityTeleport.class)
            .append(PacketPlayOutAdvancements.class)
            .append(PacketPlayOutUpdateAttributes.class)
            .append(PacketPlayOutEntityEffect.class)
            .append(PacketPlayOutRecipeUpdate.class)
            .append(PacketPlayOutTags.class)
        );
        protocolIncoming.put(PLAY, new AdvancedList<Supplier<? extends PacketIncoming>>()
            .append(PacketPlayInTeleportAccept::new)
            .append(PacketPlayInTileNBTQuery::new)
            .append(PacketPlayInDifficultyChange::new)
            .append(PacketPlayInChat::new)
            .append(PacketPlayInClientCommand::new)
            .append(PacketPlayInSettings::new)
            .append(PacketPlayInTabComplete::new)
            .append(PacketPlayInEnchantItem::new)
            .append(PacketPlayInWindowClick::new)
            .append(PacketPlayInCloseWindow::new)
            .append(PacketPlayInCustomPayload::new)
            .append(PacketPlayInBEdit::new)
            .append(PacketPlayInEntityNBTQuery::new)
            .append(PacketPlayInUseEntity::new)
            .append(PacketPlayInJigsawGenerate::new)
            .append(PacketPlayInKeepAlive::new)
            .append(PacketPlayInDifficultyLock::new)
            .append(PacketPlayInFlyingPosition::new)
            .append(PacketPlayInFlyingPositionLook::new)
            .append(PacketPlayInFlyingLook::new)
            .append(PacketPlayInFlyingOnGround::new)
            .append(PacketPlayInVehicleMove::new)
            .append(PacketPlayInBoatMove::new)
            .append(PacketPlayInPickItem::new)
            .append(PacketPlayInAutoRecipe::new)
            .append(PacketPlayInAbilities::new)
            .append(PacketPlayInBlockDig::new)
            .append(PacketPlayInEntityAction::new)
            .append(PacketPlayInSteerVehicle::new)
            .append(PacketPlayInPongPacket::new)
            .append(PacketPlayInRecipeSettings::new)
            .append(PacketPlayInRecipeDisplayed::new)
            .append(PacketPlayInItemName::new)
            .append(PacketPlayInResourcePackStatus::new)
            .append(PacketPlayInAdvancements::new)
            .append(PacketPlayInTrSel::new)
            .append(PacketPlayInBeacon::new)
            .append(PacketPlayInHeldItemSlot::new)
            .append(PacketPlayInSetCommandBlock::new)
            .append(PacketPlayInSetCommandMinecart::new)
            .append(PacketPlayInSetCreativeSlot::new)
            .append(PacketPlayInSetJigsaw::new)
            .append(PacketPlayInStruct::new)
            .append(PacketPlayInUpdateSign::new)
            .append(PacketPlayInArmAnimation::new)
            .append(PacketPlayInSpectate::new)
            .append(PacketPlayInUseItem::new)
            .append(PacketPlayInBlockPlace::new)
        );
        
        
        //Status
        protocolOutgoing.put(STATUS, new AdvancedList<Class<? extends PacketOutgoing>>()
            .append(PacketStatusOutServerInfo.class)
            .append(PacketStatusOutPong.class)
        );
        protocolIncoming.put(STATUS, new AdvancedList<Supplier<? extends PacketIncoming>>()
            .append(PacketStatusInStart::new)
            .append(PacketStatusInPing::new)
        );
        
        
        //Login
        protocolOutgoing.put(LOGIN, new AdvancedList<Class<? extends PacketOutgoing>>()
            .append(PacketLoginOutDisconnect.class)
            .append(PacketLoginOutEncryptionBegin.class)
            .append(PacketLoginOutSuccess.class)
            .append(PacketLoginOutSetCompression.class)
            .append(PacketLoginOutCustomPayload.class)
        );
        protocolIncoming.put(LOGIN, new AdvancedList<Supplier<? extends PacketIncoming>>()
            .append(PacketLoginInStart::new)
            .append(PacketLoginInEncryptionBegin::new)
            .append(PacketLoginInCustomPayload::new)
        );
        
        
        protocolOutgoingMap = new AdvancedMap<>();
        for(byte type : protocolOutgoing.keySet()) {
            List<Class<? extends PacketOutgoing>> data = protocolOutgoing.get(type);
            Map<Class<? extends PacketOutgoing>, Byte> dataMap = new AdvancedMap<>();
            
            for(byte i = 0; i < data.size(); i++) {
                dataMap.put(data.get(i), i);
            }
            protocolOutgoingMap.put(type, dataMap);
        }
    }
    
    public static List<Supplier<? extends PacketIncoming>> getIncomingPacketsForProtocol(byte type) {
        List<Supplier<? extends PacketIncoming>> result = protocolIncoming.get(type);
        if(result == null) return new AdvancedList<>();
        return result;
    }
    
    public static Map<Class<? extends PacketOutgoing>, Byte> getOutgoingPacketMapForProtocol(byte type) {
        Map<Class<? extends PacketOutgoing>, Byte> result = protocolOutgoingMap.get(type);
        if(result == null) return new AdvancedMap<>();
        return result;
    }
}
