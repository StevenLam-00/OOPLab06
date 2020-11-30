package lab6;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;

public class productGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textProductName;
	private JTextField textAmount;
	private Product product;
	private List<Product> productList;
	private storeData storeData;
	private JList list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					productGUI frame = new productGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public productGUI() {
		storeData = new storeData();
        productList = storeData.read();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 711, 497);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProductName = new JLabel("Product Name");
		lblProductName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblProductName.setBounds(25, 33, 146, 32);
		contentPane.add(lblProductName);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAmount.setBounds(25, 89, 111, 25);
		contentPane.add(lblAmount);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDescription.setBounds(25, 146, 127, 25);
		contentPane.add(lblDescription);
		
		textProductName = new JTextField();
		textProductName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textProductName.setBounds(185, 33, 184, 30);
		contentPane.add(textProductName);
		textProductName.setColumns(10);
		
		textAmount = new JTextField();
		textAmount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textAmount.setBounds(183, 89, 186, 26);
		contentPane.add(textAmount);
		textAmount.setColumns(10);
		
		JTextPane textDescription = new JTextPane();
		textDescription.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textDescription.setBounds(185, 146, 184, 114);
		contentPane.add(textDescription);
		
		JLabel lblProductList = new JLabel("Product List");
		lblProductList.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblProductList.setBounds(446, 16, 184, 59);
		contentPane.add(lblProductList);

		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int amountFormat= Integer.parseInt(textAmount.getText());
				boolean isExisted= false;
				product = null;
				 int size = productList.size();
			        for (int i = 0; i < size; i++) {
			            if (productList.get(i).getProductName().equalsIgnoreCase(textProductName.getText())== true) {
			                isExisted=true;
//			            	JOptionPane.showMessageDialog(null, "this product has been existed! Please input again",
//			            			"Result", JOptionPane.INFORMATION_MESSAGE);
//			                break;
			                product= productList.get(i);
			                Product product1 =new Product(product.getProductName(),
				        			product.getAmount()+amountFormat, product.getDescription());
					        product1.setProductName(product.getProductName());
					        product1.setAmount(product.getAmount()+amountFormat);
					        product1.setDescription(product.getDescription());
					        productList.remove(product);
					        storeData.write(productList);
					        productList.add(product1);
					        storeData.write(productList);
			            }
			        }
			        if(isExisted==false) {
			        	Product product1 =new Product(textProductName.getText(), amountFormat, textDescription.getText());
				        product1.setProductName(textProductName.getText());
				        product1.setAmount(amountFormat);
				        product1.setDescription(textDescription.getText());
				        productList.add(product1);
				        storeData.write(productList);
				        JOptionPane.showMessageDialog(null, "Adding successful");
			        }
			        list.setModel(new AbstractListModel() {
						Object[] values = new Object[productList.size()];
						public int getSize() {
							return values.length;
						}
						public Object getElementAt(int index) {
							for(int i=0; i<productList.size();i++) {
								values[i]=productList.get(i).getProductName()+"   "
							+productList.get(i).getAmount()+"   "+productList.get(i).getDescription();
							}

							return values[index];
						}
					});
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAdd.setBounds(25, 328, 111, 40);
		contentPane.add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int amountFormat= Integer.parseInt(textAmount.getText());
				 product = null;
				 int size = productList.size();
			        for (int i = 0; i < size; i++) {
			            if (productList.get(i).getProductName().equalsIgnoreCase(textProductName.getText())) {
			                product = productList.get(i);
			                break;
			            }
			        }
			        if (product != null) {
			        	if(product.getAmount()==0) {
			        		 productList.remove(product);
					         storeData.write(productList);
			        	}
			        if(product.getAmount()>0) {
			        	Product product1 =new Product(product.getProductName(),
			        			product.getAmount()-1, product.getDescription());
				        product1.setProductName(product.getProductName());
				        product1.setAmount(product.getAmount()-1);
				        product1.setDescription(product.getDescription());
				        productList.remove(product);
				        storeData.write(productList);
				        productList.add(product1);
				        storeData.write(productList);
			        }   
			          //  JOptionPane.showMessageDialog(null, "remove successfull");
			        } else {
			        	JOptionPane.showMessageDialog(null, "the product name is not exist");
			        }
			        list.setModel(new AbstractListModel() {
						Object[] values = new Object[productList.size()];
						public int getSize() {
							return values.length;
						}
						public Object getElementAt(int index) {
							for(int i=0; i<productList.size();i++) {
								values[i]=productList.get(i).getProductName()+"   "
							+productList.get(i).getAmount()+"   "+productList.get(i).getDescription();
							}

							return values[index];
						}
					});
			}
		});
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRemove.setBounds(146, 328, 127, 40);
		contentPane.add(btnRemove);
		
		list = new JList();
		list.setModel(new AbstractListModel() {
			Object[] values = new Object[productList.size()];
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				for(int i=0; i<productList.size();i++) {
					values[i]=productList.get(i).getProductName()+"   "
				+productList.get(i).getAmount()+"   "+productList.get(i).getDescription();
				}

				return values[index];
			}
		});
		list.setBounds(456, 75, 203, 346);
		contentPane.add(list);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				  product =null;
//		        int size = productList.size();
//		        for (int i = 0; i < size; i++) {
//		            if (productList.get(i).getProductName().equalsIgnoreCase(textProductName.getText())==true) {
//		                product = productList.get(i);
//		                JOptionPane.showMessageDialog(null,"Product Name: "+ product.getProductName()+
//		                		", Amount: "+product.getAmount()+", Description: "+product.getDescription()
//		                		);
//		               
//		                
//		                break;
//		            }
//		        }
//		        if (product != null) {
//		            
//		        } else {
//		            JOptionPane.showMessageDialog(null, "Not found the Product");
//		        }
				
				////////////////
				if(e.getSource().equals(btnSearch)) {
					Search s= new Search();
					// close only own's frame not close the another frame;
					s.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					s.setVisible(true);
					///////////////
					// EXIT_ON_CLOSE: close all frame has relationship
					// HIDE_ON_CLOSE : Hide the Frame when press Close botton
					// DO_NOTHING_ON_CLOSE : do nothing
					///////////////
					
				}	
				
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSearch.setBounds(299, 326, 117, 40);
		contentPane.add(btnSearch);
		
		
		
		
	}
	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
	 public storeData getProductDao() {
		return storeData;
	}

	public void setProductDao(storeData storeData) {
		this.storeData = storeData;
	}
}
