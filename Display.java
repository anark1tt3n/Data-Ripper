import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;        
 
@SuppressWarnings("serial")
public class Display extends JPanel implements ActionListener{
	private JButton open;
	private JFileChooser fChooser;
	private JFileChooser sChooser;
	private JButton run;
	int returnVal;
	private JSpinner spinner;
	private JSpinner spinner2;
	private File file;
	int length;
    File workingDirectory = new File(System.getProperty("user.dir"));
    String fPath;
    
    public Display() {
        setPreferredSize(new Dimension(270, 117));
        setLayout(null);
        
        open = new JButton("Select a file");
        add(open);
        open.setBounds(83,11,100,17);
        open.addActionListener(this);
        
        run = new JButton("Run");
        add(run);
        run.setBounds(96,90,78,17);
        run.setEnabled(false); 
        run.addActionListener(this);
        
        JLabel j = new JLabel(" Value to parse");
        add(j);
        j.setBounds(5, 35, 85, 15);
        
        SpinnerModel num = new SpinnerNumberModel(1,1,100,1);
        spinner = new JSpinner(num);
        add(spinner);
        spinner.setBounds(27, 53, 50, 25);

        JLabel g = new JLabel("Character divider");
        add(g);
        g.setBounds(170, 35, 100, 15);

        SpinnerListModel divide = new SpinnerListModel(new String[] {"\\s+","\\t" , ":" , "|" , "\\" , "/"});
        spinner2 = new JSpinner(divide);
        add(spinner2);
        spinner2.setBounds(195, 53, 50, 25);

        setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
    	if(e.getSource() == open){
    		fChooser = new JFileChooser();
            fChooser.setCurrentDirectory(workingDirectory);
            fChooser.setDialogTitle("Select a text(.txt) file.");
    		returnVal = fChooser.showOpenDialog(null);
    		fChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    		if(returnVal == JFileChooser.APPROVE_OPTION){
    			file = fChooser.getSelectedFile();
    			System.out.println(file);
    			fPath = file.getAbsolutePath();
    			try {
					length = PText.count(fPath);
					run.setEnabled(true);
				} catch (IOException nf) {
					nf.printStackTrace(System.out);
					run.setEnabled(false);
				}
    			
    		}
    	}else if(e.getSource() == run){
    		sChooser = new JFileChooser();
            sChooser.setCurrentDirectory(file);
            sChooser.setDialogTitle("Save the file where?");
    		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
    		sChooser.setFileFilter(filter);
    		returnVal = sChooser.showSaveDialog(null);
    		if(returnVal == JFileChooser.APPROVE_OPTION && sChooser.getSelectedFile().getPath() != null){
    			String directory = sChooser.getSelectedFile().getPath();
    			System.out.println(directory);
    			String filename = sChooser.getSelectedFile().getName();
    			try {
    			    int n1 = (int)spinner.getValue();
    			    String st = (String)spinner2.getValue();
    			    PText.parseText(file, length, directory,n1,st);
//					System.out.println(file);
//					System.out.println(file);
//					System.out.println(length);
//					System.out.println(directory);
					JOptionPane.showMessageDialog(this, "Success! Output as " + filename + " under " + directory.substring(0,directory.length()-filename.length()));
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(this, "IOException, maybe no file chosen?");
					run.setEnabled(false);
					System.err.println(file + "/n" + length + "/n" + filename);
				} catch(ArrayIndexOutOfBoundsException jk){
					JOptionPane.showMessageDialog(this, "Invalid parameter for \"Value to Parse\" chosen. Please fix it.");
					spinner.setValue(0);
					
				}
    		}
    		
    	}
    	
    }
}