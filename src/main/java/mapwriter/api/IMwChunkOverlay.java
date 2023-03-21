package mapwriter.api;

import java.awt.Point;

public interface IMwChunkOverlay {
    Point getCoordinates();

    int getColor();

    float getFilling();

    boolean hasBorder();

    float getBorderWidth();

    int getBorderColor();
}
