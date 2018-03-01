public class BinarySearchTree
{
	/*
	 * Creating the binary search tree
	 */
	private class Node
	{
		private String theString;
		private int counter;
		private Node leftChild;
		private Node rightChild;
		private Node parent;
		
		private Node(String theString)
		{
			this.theString = theString;
			this.counter = 1;
			this.leftChild = null;
			this.rightChild = null;
			this.parent = null;
		}
		
		//used to get the size of the tree.
		private void incrementCounter()
		{
			this.counter++;
		}
		
		//setter for the input
		private void setString(String theString)
		{
			this.theString = theString;
		}
		
		//getter for the input
		private String getString()
		{
			return this.theString;
		}
		
		//setting left child of the tree
		private void setLeftChild(Node leftChild)
		{
			this.leftChild = leftChild;
		}
		
		//getting left child of the tree
		private Node getLeftChild()
		{
			return this.leftChild;
		}
		
		//setting right child of the tree
		private void setRightChild(Node rightChild)
		{
			this.rightChild = rightChild;
		}
		
		//getting right child of the tree
		private Node getRightChild()
		{
			return this.rightChild;
		}
		
		//setting the parent of the tree of the tree
		private void setParent(Node parent)
		{
			this.parent = parent;
		}
		
		//getting the parent of the tree of the tree
		private Node getParent()
		{
			return this.parent;
		}
		
		private int getCounter()
		{
			return this.counter;
		}
	}
	
	private Node root;
	private String aString = "";
	private String theString[];
	
	private Node getRoot()
	{
		return this.root;
	}
	
	private void setRoot(Node root)
	{
		this.root = root;
	}
	
	//check if the tree is empty
	public boolean isEmpty()
	{
		return getRoot() == null;
	}
	
	//method to insert into the tree.
	//overloaded
	public void insert(String theString)
	{
		Node newNode = new Node(theString);
		
		if(isEmpty())
		{
			setRoot(newNode);
		}
		else
		{
			insert(newNode, getRoot());
		}
	}
	
	public void insert(Node n, Node r)
	{
		if(n.getString().equalsIgnoreCase(r.getString())) 
		{
			r.incrementCounter();
		}
		else if(n.getString().compareToIgnoreCase(r.getString()) < 0) 
		{
			if(r.getLeftChild() == (null)) 
			{
				r.setLeftChild(n);
				n.setParent(r);
			}
			else 
			{
				insert(n, r.getLeftChild());
			}
		}
		else 
		{
			if(r.getRightChild() == (null)) 
			{
				r.setRightChild(n);
				n.setParent(r);
			}
			else // right is occupied
			{
				insert(n, r.getRightChild());
			}
		}
	}
	
	//method to search the tree for an input
	//overloaded
	public boolean searchResult(String theString)
	{
		Node searchIt = search(theString);
		
		if(searchIt != null)
			return true;
		
		return false;
	}
	
	public Node search(String theString)
	{
		if(isEmpty())
			return null;
		else
			return search(getRoot(), theString);
	}
	
	public Node search(Node r, String theString)
	{
		if(r == null)
			return null;
		if(theString.equalsIgnoreCase(r.getString()))
			return r;
		else if(theString.compareToIgnoreCase(r.getString()) < 0)
			return search(r.getLeftChild(), theString);
		else if(theString.compareTo(r.getString()) > 0)
			return search(r.getRightChild(), theString);
		else
			return null;
	}
	
	//checking if what user search for is in the tree
	public boolean isThere(Node r,String theString)
	{
            if (theString.equalsIgnoreCase(r.getString()))
                  return true;
            	
            else if (theString.compareToIgnoreCase(r.getString()) <= 0) 
            {
                  if (r.getLeftChild().equals(null))
                        return false;
                  else
                        return isThere(r.getLeftChild(), theString);
            } else if (theString.compareTo(r.getString()) >= 0) 
            {
                  if (r.getRightChild().equals(null))
                        return false;
                  else
                        return isThere(r.getRightChild(),theString);
            }
            return false;
      
	}
	
	public String delete(String theString)
	{
		Node toDelete = search(theString);
		String isFound = "";
		
		if(toDelete == null) 
			return isFound = "Node not in tree" + " \u2622";
		
		if(toDelete == getRoot()) 
		{
			if(getRoot().getLeftChild() == null && getRoot().getRightChild() == null)
			{
				destroy();
			}
			else if(getRoot().getLeftChild() != null && getRoot().getRightChild() == null)
			{
				setRoot(getRoot().getLeftChild());
			}
			else if(getRoot().getLeftChild() == null && getRoot().getRightChild() != null)
			{
				setRoot(getRoot().getRightChild());
			}
			else
			{
				Node theMinimum = findMinimum(getRoot().getRightChild());
				String tempString = getRoot().getString();
				getRoot().setString(theMinimum.getString());
				theMinimum.setString(tempString);
				
				if(theMinimum.getParent().getLeftChild() == theMinimum)
				{
					theMinimum.getParent().setLeftChild(null);
				}
				else
				{
					theMinimum.getParent().setRightChild(null);
				}
			}
		}
		else // it's not the root, it's somewhere else in the tree
		{
			if(toDelete.getLeftChild() == null && toDelete.getRightChild() == null)
			{
				if(toDelete.getParent().getLeftChild() == toDelete)
				{
					toDelete.getParent().setLeftChild(null);
				}
				else
				{
					toDelete.getParent().setRightChild(null);
				}
			}
			else if(toDelete.getLeftChild() != null && toDelete.getRightChild() == null)
			{
				if(toDelete.getParent().getLeftChild() == toDelete)
				{
					toDelete.getParent().setLeftChild(toDelete.getLeftChild());
				}
				else
				{
					toDelete.getParent().setRightChild(toDelete.getLeftChild());
				}
			}
			else if(toDelete.getLeftChild() == null && toDelete.getRightChild() != null)
			{
				if(toDelete.getParent().getLeftChild() == toDelete)
				{
					toDelete.getParent().setLeftChild(toDelete.getRightChild());
				}
				else
				{
					toDelete.getParent().setRightChild(toDelete.getRightChild());
				}
			}
			else
			{
				Node minimum = findMinimum(toDelete.getRightChild());
				String temp = toDelete.getString();
				toDelete.setString(minimum.getString());
				minimum.setString(temp);
				
				if(minimum.getParent().getLeftChild() == minimum)
				{
					minimum.getParent().setLeftChild(null);
				}
				else
				{
					minimum.getParent().setRightChild(null);
				}
			}
		}
		return isFound = "Node deleted" + " \u2713";
	}
	
	//balance the tree
	public Node balance(String theString[],int beg, int end)
	{
		if(beg >end)
		{
			return null;
		}
		 int middle = (beg+end)/2;
		 Node newNode = new Node(theString[middle]);
		 newNode.getLeftChild() = balance(theString,beg,end);
	}
	
	public String[] newOrder()
	{
		aString = "";
		if(isEmpty())
		{
			return null;
		}
		else
		{
			return newOrder(getRoot());
		}
	}
	public String[] newOrder(Node n)
	{
		if(n != null)
		{
			inOrder(n.getLeftChild());
			for(int i=0;i<size();i++)
			{
				theString[i]= n.getString() + "\n";
			}
			inOrder(n.getRightChild());
		}
		return theString;
	}
	
	//inserting into the tree inorder
	//overloaded
	public String inOrder()
	{
		aString = "";
		if(isEmpty())
		{
			return("Tree is empty!");
		}
		else
		{
			return inOrder(getRoot());
		}
	}
	
	public String inOrder(Node n)
	{
		if(n != null)
		{
			inOrder(n.getLeftChild());
			aString += n.getString() + "\n";
			inOrder(n.getRightChild());
		}
		
		return aString;
	}
	
	//inserting into the tree preorder
	//overloaded
	public String preOrder()
	{
		aString = "";
		if(isEmpty())
		{
			return("Tree is empty!");
		}
		else
		{
			return preOrder(getRoot());
		}
	}
	
	public String preOrder(Node n)
	{
		if(n != null)
		{
			aString += n.getString() + "\n";
			preOrder(n.getLeftChild());
			preOrder(n.getRightChild());
		}
		return aString;
	}
	
	//inserting into the tree postorder
	//overloaded
	public String postOrder()
	{
		aString = "";
		if(isEmpty())
		{
			return("Tree is empty!");
		}
		else
		{
			return postOrder(getRoot());
		}
	}
	
	public String postOrder(Node n)
	{
		if(n != null)
		{
			postOrder(n.getLeftChild());
			postOrder(n.getRightChild());
			aString += n.getString() + "\n";
		}
		return aString;
	}
	
	//find the minimum value of the entry
	public Node findMinimum(Node n)
	{
		if(n.getLeftChild() == null)
		{
			return n;
		}
		else
		{
			return findMinimum(n.getLeftChild());
		}
	}
	
	//method to find the depth of the tree
	//overloaded
	public int depth()
	{
		int size;
		size =depth(getRoot());
		return size;
	}
	
	public int depth(Node n)
	{

		if(n == null)
		{
			return 0;
		}	
		return 1 + Math.max(depth(n.getLeftChild()),depth(n.getRightChild()));
	}
	
	//getting the size of the tree
	//overloaded
	public int size()
	{
		int currentSize;
		currentSize = size(getRoot());
		return currentSize;
	}
	
	public int size(Node n)
	{
		if(n == null)
		{
			return 0;
		}	
		return 1 + (depth(n.getLeftChild())+depth(n.getRightChild()));
	}

	//destroy the tree
	public String destroy()
	{
		String itsGone ="";
		setRoot(null);
		return itsGone = "NOOOOOO. It's gone forever. Hope your happy";
	}
}