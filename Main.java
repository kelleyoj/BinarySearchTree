import java.awt.EventQueue;

public class Main 
{
	public static void main(String[] args)
	{
		BinarySearchTree tree = new BinarySearchTree();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu(tree);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}