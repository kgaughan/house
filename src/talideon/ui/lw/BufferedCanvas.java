/*
 * BufferedCanvas.java
 *
 * Created on 18 June 2006, 01:18
 */

package talideon.ui.lw;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author kgaughan
 */
public abstract class BufferedCanvas extends Canvas {

    public BufferedCanvas() {
        super();
    }

    public void paint(Graphics g) {
        Dimension d = getSize();
        Image buffer = createImage(d.width, d.height);
        Graphics gBuffer = buffer.getGraphics();
        gBuffer.setClip(g.getClip());
        super.paint(gBuffer);
        bufferedPaint(gBuffer);
        g.drawImage(buffer, 0, 0, null);
        gBuffer.dispose();
    }

    protected abstract void bufferedPaint(Graphics g);
}
