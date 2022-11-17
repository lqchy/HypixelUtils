package me.lachy.hypixelutils;

import com.cecer1.projects.mc.cecermclib.common.modules.ModuleRegistrationCallback;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.lachy.hypixelutils.command.RotationCommand;
import me.lachy.hypixelutils.modules.locraw.LocrawModule;
import me.lachy.hypixelutils.util.Scheduler;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod(modid = HypixelUtils.MOD_ID, version = HypixelUtils.VERSION)
public class HypixelUtils {

    public static final String MOD_ID = "hypixelutils";
    public static final String VERSION = "1.0";
    public static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    @Mod.EventHandler
    public void onFMLPreInitialization(FMLPreInitializationEvent event) {
        ModuleRegistrationCallback.EVENT.register(ctx -> {
            System.out.println("Module registration!");
            ctx.registerModule(new LocrawModule());
        });
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new RotationCommand());
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            Scheduler.get().runTasks();
        }
    }
}
