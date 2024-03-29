package useless.betabuddy.mixin;

import net.minecraft.core.net.packet.Packet;
import net.minecraft.core.net.packet.Packet3Chat;
import net.minecraft.server.net.PlayerList;
import net.minecraft.server.net.handler.NetLoginHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = NetLoginHandler.class, remap = false)
public class NetLoginHandlerMixin {
	@Redirect(method = "doLogin(Lnet/minecraft/core/net/packet/Packet1Login;)V",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/server/net/PlayerList;sendPacketToAllPlayers(Lnet/minecraft/core/net/packet/Packet;)V", ordinal = 0))
	private void changeLoginMessage(PlayerList instance, Packet i){
		String name = ((Packet3Chat)i).message.replace(" joined the game.", "");
		instance.sendPacketToAllPlayers(new Packet3Chat(name + " has joined Beta Buddies!"));
	}
}
