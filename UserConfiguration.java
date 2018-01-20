import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class UserConfiguration {
	
	
	static ArrayList<String> currencyPair = new ArrayList<String>();
	static ArrayList<Float> targetRate = new ArrayList<Float>();
	
	private static String[] Symbols = { "EURUSD","USDJPY",
			"GBPUSD","USDCHF","EURCHF","AUDUSD","USDCAD","NZDUSD",
			"EURJPY","GBPJPY","EURGBP","CHFJPY","GBPCHF","EURAUD","EURCAD",
			"AUDCAD","AUDJPY","CADJPY","NZDJPY","GBPAUD","AUDNZD","AUDCHF","EURNZD","USDHKD","USDMXN","GBPNZD","USDSEK","EURSEK",
			"EURNOK","USDNOK","USDZAR","GBPCAD","USDTRY","EURTRY","NZDCHF","CADCHF","NZDCAD","US30","SPX500","NAS100","UK100",
			"GER30","ESP35","FRA40","HKG33","JPN225","AUS200","USOil","UKOil","XAUUSD","XAGUSD","USDOLLAR","USDILS","TRYJPY",
			"USDCNH","NGAS","Copper","EUSTX50","Bund"};
	
	JTextField targetRateEntered = new JTextField(15);	
	JComboBox<String> currencyPairEntered = new JComboBox<String>(Symbols);
	
	
	private void getUserInputs(){
		
		JFrame frame = new JFrame();
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setTitle("User Configuration");
	    frame.setSize(400, 400);
	    frame.setResizable(false);

	    JPanel panel = new JPanel();
	    
	    panel.setLayout(new GridLayout(6,1));
	    frame.add(panel);
	    		
		JLabel label1 = new JLabel("  Please Select the Currency Pair : ");
		panel.add(label1);
		
		currencyPairEntered.setSelectedIndex(0);
		panel.add(currencyPairEntered);
		
		JLabel label2 = new JLabel("Please Enter the Target Rate :     ");
		panel.add(label2);
		
		targetRateEntered.setText("");
		panel.add(targetRateEntered);
		
		JLabel label3 = new JLabel(" ");
		label3.setVisible(true);
		panel.add(label3);

		JButton	button = new JButton("Click Ok");
		panel.add(button);
				
		button.addActionListener(new ActionListener() {
			
		    public void actionPerformed(ActionEvent event) {
		    	
		    	try{

					frame.setVisible(false);
					frame.dispose();
					
					// Perform check for validating Target Value entered by User
					
					String targetRateAsString = targetRateEntered.getText().trim();
					float targetRateNumber = Float.parseFloat(targetRateAsString);	
					
					// Add Currency Pair & Target Rate to the User Entered List 
							
					currencyPair.add((String)currencyPairEntered.getSelectedItem());
			       	targetRate.add(targetRateNumber);
			       
					JFrame frame1 = new JFrame();
				    frame1.setVisible(true);
				    frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				    frame1.setTitle("User Configuration");
				    frame1.setSize(400, 400);
				    frame1.setResizable(false);

				    JPanel panel1 = new JPanel();
				    panel1.setLayout(new GridLayout(3,1));
				    
				    frame1.add(panel1);
					
				    JLabel label3 = new JLabel("  Another Currency Pair & Target Rate :     ");
				    label3.setVisible(true);
					panel1.add(label3);
					
					JButton yesButton = new JButton("Yes");
					yesButton.setVisible(true);
					panel1.add(yesButton);
					
					JButton quitButton = new JButton("No. Quit");
					quitButton.setVisible(true);
					panel1.add(quitButton);
						
					yesButton.addActionListener(new ActionListener() {
					    public void actionPerformed(ActionEvent e) {
					    	frame1.setVisible(false);
							frame1.dispose();
					    	getUserInputs();
					      }
					    }
					  );
					
					quitButton.addActionListener(new ActionListener() {
					    public void actionPerformed(ActionEvent e) {
					    	System.out.println("Check the UserInputs.txt for User Configuration Inputs...");
					    	
					        try{
					            // Create PrintWriter 
					        	
					        	PrintWriter out = new PrintWriter("UserInputs.txt");
					        	for (int userConf = 0; userConf < currencyPair.size(); userConf++) {
					            	out.print(currencyPair.get(userConf));
					            	out.print("\t");
					            	out.print(targetRate.get(userConf));
					            	//out.print("\t");
					            	//out.print(isTargetRateReached.get(userConf));
					            	out.println();
					            	out.flush();
					            }
					            //Close the PrintWriter
					            out.close();
				            }
					        catch (Exception exp){
					              System.err.println("Error: " + exp.getMessage());
				            }
					        System.exit(0);		    	
					    	//XMLParser.ReadRatesXMLURL();
					    	//CheckTargetRates.checkRates();
					   }
					  }
					);
		    	}catch(NumberFormatException e){
					
		    		JFrame frame2 = new JFrame();
				    frame2.setVisible(true);
				    frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				    frame2.setTitle("User Configuration");
				    frame2.setSize(250, 250);
				    frame2.setResizable(false);

				    JPanel panel2 = new JPanel();
				    panel2.setLayout(new GridLayout(3,1));
				    frame2.add(panel2);
					
				    JLabel label1 = new JLabel("  Targer Rate is Invalid... ");
				    label1.setVisible(true);
					panel2.add(label1);
					
					JLabel label2 = new JLabel("  Please Enter Targer Rate again...");
				    label2.setVisible(true);
					panel2.add(label2);
					
					JButton button = new JButton("OK");
					button.setVisible(true);
					panel2.add(button);
					
					button.addActionListener(new ActionListener() {
					    public void actionPerformed(ActionEvent e) {
					    	getUserInputs();
					    	frame2.setVisible(false);
							frame2.dispose();
					      }
					    }
					  );
				}
				}
		    }
		  );
		
	}

	
	public static void main(String args[]){
		UserConfiguration userConf = new UserConfiguration();
		userConf.getUserInputs();
	}
}