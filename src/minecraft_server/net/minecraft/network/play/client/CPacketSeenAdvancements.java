package net.minecraft.network.play.client;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.util.ResourceLocation;

public class CPacketSeenAdvancements implements Packet<INetHandlerPlayServer>
{
    private CPacketSeenAdvancements.Action field_194166_a;
    private ResourceLocation field_194167_b;

    /**
     * Reads the raw packet data from the data stream.
     */
    public void readPacketData(PacketBuffer buf) throws IOException
    {
        this.field_194166_a = (CPacketSeenAdvancements.Action)buf.readEnumValue(CPacketSeenAdvancements.Action.class);

        if (this.field_194166_a == CPacketSeenAdvancements.Action.OPENED_TAB)
        {
            this.field_194167_b = buf.func_192575_l();
        }
    }

    /**
     * Writes the raw packet data to the data stream.
     */
    public void writePacketData(PacketBuffer buf) throws IOException
    {
        buf.writeEnumValue(this.field_194166_a);

        if (this.field_194166_a == CPacketSeenAdvancements.Action.OPENED_TAB)
        {
            buf.func_192572_a(this.field_194167_b);
        }
    }

    /**
     * Passes this Packet on to the NetHandler for processing.
     */
    public void processPacket(INetHandlerPlayServer handler)
    {
        handler.func_194027_a(this);
    }

    public CPacketSeenAdvancements.Action func_194162_b()
    {
        return this.field_194166_a;
    }

    public ResourceLocation func_194165_c()
    {
        return this.field_194167_b;
    }

    public static enum Action
    {
        OPENED_TAB,
        CLOSED_SCREEN;
    }
}
