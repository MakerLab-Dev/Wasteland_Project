package wasteland.renderer;

public final class RenderLayers {
    // A RenderLayers is a integer which represents
    // the layer that should be rendered.

    // None layer is an empty layer.
    public static final int None = 0;

    // Main layers
    // World layer is the layer that contains the
    // world.
    public static final int World = 1;
    // Journal layer is the layer that contains the
    // journal.
    public static final int Journal = 2;

    // Overlay layers
    // Help layer is the layer that contains the
    // help text.
    public static final int Help = 3;
    // Inventory layer is the layer that contains
    // the player's inventory.
    public static final int Inventory = 4;

    // Current layer is the layer that is currently
    // being rendered.
    private int renderLayer;

    // Constructs a new RenderLayers with the
    // specified layer.
    public RenderLayers(int layer) {
        this.renderLayer = layer;
    }

    // Returns the current render layer.
    public int getRenderLayer() {
        return this.renderLayer;
    }

    // Sets the current render layer.
    public void setRenderLayer(int layer) {
        this.renderLayer = layer;
    }
}
