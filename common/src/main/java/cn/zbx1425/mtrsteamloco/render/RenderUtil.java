package cn.zbx1425.mtrsteamloco.render;

import cn.zbx1425.mtrsteamloco.ClientConfig;
import cn.zbx1425.mtrsteamloco.MainClient;
import cn.zbx1425.sowcerext.multipart.MultipartContainer;
import cn.zbx1425.sowcerext.multipart.MultipartUpdateProp;
import cn.zbx1425.sowcer.math.Matrix4f;
import mtr.data.TrainClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.player.Player;

public class RenderUtil {

    public static final int LEVEL_SOWCER = 2;
    public static final int LEVEL_BLAZE = 1;
    public static final int LEVEL_NONE = 0;

    public static MultiBufferSource commonVertexConsumers = null;

    public static boolean shouldSkipRenderTrain(TrainClient train) {
        if (!ClientConfig.enableTrainRender) return true;
        if (ClientConfig.hideRidingTrain) {
            Player player = Minecraft.getInstance().player;
            if (player != null) {
                return train.isPlayerRiding(player);
            }
        }
        return false;
    }

    public static String getRenderStatusMessage() {
        return "\n=== NTE Rendering Status ===\n"
                + "Draw Calls: " + MainClient.profiler.drawCallCount
                + ", Batches: " + MainClient.profiler.batchCount
                + "\n"
                + "Faces: " + MainClient.profiler.singleFaceCount + " non-instanced"
                + ", " + MainClient.profiler.instancedFaceCount + " instanced"
                + ", " + (MainClient.profiler.singleFaceCount + MainClient.profiler.instancedFaceCount) + " total"
                + "\n"
                + "Faces via Blaze3D: " + MainClient.profiler.blazeFaceCount
                + "\n"
                + "Loaded Models: " + MainClient.modelManager.loadedRawModels.size()
                + ", Uploaded VAOs: " + MainClient.modelManager.uploadedVertArraysCount
                ;
    }
}
