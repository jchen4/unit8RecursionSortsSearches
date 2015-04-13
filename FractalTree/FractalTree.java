import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;

public class FractalTree extends JPanel
{
    private double branchRatio = .75;
    private double minBranchLength = 25;
    private double angleChangePos = .4;
    private double angleChangeNeg = .4;
    private double circle = Math.PI * 2;
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        double trunkLength = 200;
        recursiveDraw(g2, getWidth()/2, getHeight(), trunkLength, Math.PI/2, 100, 50, 10);
    }

    private void recursiveDraw(Graphics2D g2, double x, double y, double length, double angle, int r, int gr, int b)
    {
        if(length < minBranchLength)
        {
            return;
        }
        
        double x2 = x + ((length*branchRatio) * Math.cos(angle));
        double y2 = y - ((length*branchRatio) * Math.sin(angle));
        Line2D.Double branch = new Line2D.Double(x, y, x2, y2);
       
        g2.setColor(new Color(r, gr, b));
        g2.draw(branch);
        r = (int)(r*.5);
        gr = (int)(gr*.5);
        b = (int)(b*.5);
        
        recursiveDraw(g2, x2, y2, length*branchRatio, (angle+angleChangePos)%circle, r, gr, b);
        recursiveDraw(g2, x2, y2, length*branchRatio, (angle-angleChangeNeg)%circle, r, gr, b);
        recursiveDraw(g2, x2, y2, length*branchRatio, (angle+angleChangePos/2)%circle, r, gr, b);
        recursiveDraw(g2, x2, y2, length*branchRatio, (angle-angleChangePos/2)%circle,r, gr, b);
        
    }
    
    public static void main()
    {
        JFrame frame = new JFrame();
        frame.setSize(800,600);
        frame.setTitle("arinnairsuckatprogramming");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FractalTree panel = new FractalTree();
        frame.add(panel);
        frame.setVisible(true);
    }
}
