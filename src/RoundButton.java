import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

class RoundButton extends JButton {
    private final int cornerRadius;

    public RoundButton(String label) {
        super(label);
        setContentAreaFilled(false); // This will make the button transparent
        setFocusPainted(false); // This will remove the focus indicator
        setBorderPainted(false); // This will remove the border
        setOpaque(false); // This will make the button transparent
        setForeground(Color.WHITE); // Set text color
        setBackground(Color.BLUE); // Set background color
        setFont(new Font("Arial", Font.BOLD, 16)); // Set font
        cornerRadius = 20; // Set corner radius
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius));
        super.paintComponent(g2);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Do not paint border
    }
}
