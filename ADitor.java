import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.swing.*;
class util
{
	public static int iInput(String s)
	{
		int n=0;
		try
		{
			n=Integer.parseInt(JOptionPane.showInputDialog(null,s));
		}
		catch(Exception e){}
		return n;
	}
	public static double dInput(String s)
	{
		double n=0;
		try
		{
			n=Double.parseDouble(JOptionPane.showInputDialog(null,s));
		}
		catch(Exception e){}
		return n;
	}
	public static String sInput(String s)
	{
		String n="";
		try
		{
			n=(JOptionPane.showInputDialog(null,s));
		}
		catch(Exception e){}
		return n;
	}
	public static int iOption(String msg,String title,String[] opt)
	{
		int option=0;
		try
		{
			option=JOptionPane.showOptionDialog(null,msg,title,JOptionPane.YES_OPTION,JOptionPane.PLAIN_MESSAGE,null,opt,0);
		}
		catch(Exception e)
		{}
		return option;
	}
	public static void display(String s)
	{
		JOptionPane.showMessageDialog(null,s);
	}
}
class ADitor extends Frame implements ActionListener
{
    TextArea txta;
    Panel pan,panhead;
    GridBagLayout gb;
    GridBagConstraints gbc;
    Button save,open,exit,newFile,font,run,about;
    int flg;
    String fileName;
    ADitor()
    {
        pan=new Panel();
        gb=new GridBagLayout();
        gbc=new GridBagConstraints();
        setLayout(gb);
        panhead=new Panel();
        run=new Button("RUN");
        about=new Button("ABOUT");
        save=new Button("SAVE");
        open=new Button("OPEN");
        exit=new Button("EXIT");
        font=new Button("SET FONT");
        newFile=new Button("NEW");
        setTitle("ADitor - New File");
        panhead.setLayout(new GridLayout(1,3,5,5));
        pan.setLayout(new GridLayout(1,1,5,5));
        txta=new TextArea();
        save.addActionListener(this);
        exit.addActionListener(this);
        open.addActionListener(this);
        newFile.addActionListener(this);
        run.addActionListener(this);
        font.addActionListener(this);
        panhead.add(newFile);
        panhead.add(save);
        panhead.add(open);
        panhead.add(exit);
        panhead.add(font);
        panhead.add(run);
        panhead.add(about);
        about.addActionListener(this);
        flg=0;
        txta.setSize(400,400);
        pan.add(txta);
        gbc.ipady=15;
        gbc.weightx=1.0;
        gbc.weighty=-1.0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridheight=1;
        gbc.gridwidth=GridBagConstraints.REMAINDER;
        gb.setConstraints(panhead,gbc);
        add(panhead);
        gbc.weightx=1.0;
        gbc.weighty=1.0;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.gridwidth=GridBagConstraints.REMAINDER;
        gbc.gridheight=GridBagConstraints.REMAINDER;
        gb.setConstraints(pan,gbc);
        add(pan);
        Font a=new Font("MyFont",1,15);
        txta.setFont(a);
        save.setSize(100,600);
        exit.setSize(100,600);
        open.setSize(100,600);
        setResizable(true);
        setSize(850,300);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        Button b=(Button)e.getSource();
        if(b==exit)
        {
            int opt=0;
            String[] options={"Save and Exit","Exit","Cancel"};
            opt=util.iOption("Are you Sure ","filename",options);
            if(opt==2)
                return;
            else if(opt==0)
            {
                try
                {
                    if(flg==0)
                    {
                        String fnm=util.sInput("Enter the File name : ");
                        fileName=fnm;
                        flg=1;
                    }
                    String data=new String(txta.getText());
                    byte[] d=new byte[data.length()];
                    int i=0;
                    for(i=0;i<data.length();i++)
                        d[i]=(byte)data.charAt(i);
                    FileOutputStream fos=new FileOutputStream(fileName);
                    fos.write(d);
                    fos.close();
                    setTitle("ADitor - "+fileName);
                }catch(Exception e1){}
            }
            System.exit(0);
        }
        else if(b==about)
        {
            util.display("The Software is developed by Akash Deshmukh");
        }
        else if(b==save)
        {
            try
            {
                if(flg==0)
                {
                    //String fnm=util.sInput("Enter the File name : ");
                    JFileChooser chooser = new JFileChooser();
                    int returnVal = chooser.showSaveDialog(this);
                    if(returnVal == JFileChooser.APPROVE_OPTION) {
                       //System.out.println("You chose to save file: " + chooser.getSelectedFile().getAbsolutePath());
                    }
                    fileName=chooser.getSelectedFile().getAbsolutePath();
                    flg=1;
                }
                String data=new String(txta.getText());
                byte[] d=new byte[data.length()];
                int i=0;
                for(i=0;i<data.length();i++)
                    d[i]=(byte)data.charAt(i);
                FileOutputStream fos=new FileOutputStream(fileName);
                fos.write(d);
                fos.close();
                setTitle("ADitor - "+fileName);
            }catch(Exception e1){}
        }
        else if(b==newFile)
        {
            flg=0;
            fileName="New File";
            setTitle("ADitor - "+fileName);
            txta.setText("");
        }
        else if(b==font)
        {
            int no=util.iInput("Enter the Font Size : ");
            Font a=new Font("MyFont",1,no);
            txta.setFont(a);
            txta.setText(txta.getText());
        }
        else if(b==run)
        {
            String onlyClass=new String();
            int i=0;
            String onlyFilename="";
            int bc=fileName.length()-1;
            while(bc>=0 && fileName.charAt(bc)!='\\')
            {
                //System.out.print(fileName.charAt(bc));
                 bc--;
            }
            //System.out.print(bc);
            for(int tmpint=bc+1;tmpint<fileName.length();tmpint++)
                onlyFilename+=fileName.charAt(tmpint);
            String tmp="";
            while(i<onlyFilename.length() && onlyFilename.charAt(i)!='.')
                onlyClass+=onlyFilename.charAt(i++);
            String ext=new String();
            int index=fileName.indexOf(onlyFilename);
            String filePath="";
            for(int jtmp=0;jtmp<index;jtmp++)
                filePath+=fileName.charAt(jtmp);
            i++;
            while(i<onlyFilename.length())
                ext+=onlyFilename.charAt(i++);
            if(ext.equals("java")==false)
            {
                util.display("Sorry Could not run\nYou need to run a java File");
                return;
            }
            //System.out.println("Filename is "+fileName+"\nonlyFilename is "+onlyFilename+"\nfilePath is "+filePath+"\nonlyClass is "+onlyClass);
            int flg1=0;
            try {
                processCompile = Runtime.getRuntime().exec("start cmd.exe");
            } catch (IOException e97) {
                // TODO Auto-generated catch block
                e97.printStackTrace();
            }
            Process processCompile=null;
            try {
                processCompile = Runtime.getRuntime().exec("javac "+fileName);
            } catch (IOException e98) {
                // TODO Auto-generated catch block
                e98.printStackTrace();
            }
            String line = null;
            String finalline="";
            try
            {
              BufferedReader in = new BufferedReader(new InputStreamReader(processCompile.getInputStream()));
              while ((line = in.readLine()) != null) {
                  finalline+=line+"\n";
              }
            }
            catch(Exception e97)
            {

            }
            if(finalline.length()==0)
            {
                Process processRun = null;
                try {
                    processRun = Runtime.getRuntime().exec("java -cp "+filePath+" "+onlyClass);
                } catch (IOException e99) {
                    // TODO Auto-generated catch block
                    e99.printStackTrace();
                }
                try {
                    printLines("", processRun.getInputStream());
                    printLines("", processRun.getErrorStream());
                } catch (Exception e100) {
                    // TODO Auto-generated catch block
                    e100.printStackTrace();
                }
            }
            else
            {
                System.out.println(finalline);
            }
            /*try
            {
                ProcessBuilder builder = new ProcessBuilder("javac",fileName);
                builder.redirectErrorStream(true);
                Process p = builder.start();
                BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line;
                while (true)
                {
                    line = r.readLine();
                    if (line == null)
                    {
                            break;
                    }
                    System.out.println(line);
                    flg1=1;
                }
            }catch(Exception e3){}
            if(flg1==0)
            {
                System.out.println("\n\n\nCompiled Successfully\n\n\nOutput :\n");
                try
                {
                    ProcessBuilder builder1 = new ProcessBuilder("java",onlyClass);
                    builder1.redirectErrorStream(true);
                    Process p1 = builder1.start();
                    BufferedReader r1 = new BufferedReader(new InputStreamReader(p1.getInputStream()));
                    String line1;
                    while (true)
                    {
                        line1 = r1.readLine();
                        if(line1==null)
                                break;
                        System.out.println(line1);
                    }
                }catch(Exception e5){}
            }*/
        }
        else
        {
            try
            {
                //String fnm=util.sInput("Enter the File name : ");
                JFileChooser chooser = new JFileChooser();
                int returnVal = chooser.showOpenDialog(this);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                   //System.out.println("You chose to open this file: " + chooser.getSelectedFile().getAbsolutePath());
                }
                File f=new File(chooser.getSelectedFile().getAbsolutePath());
                flg=1;
                fileName=chooser.getSelectedFile().getAbsolutePath();
                if(f.exists()==false)
                {
                    System.out.println("File do not Exists");
                    return;
                }
                FileInputStream fis=new FileInputStream(f);
                byte[] bt=new byte[(int)f.length()];
                fis.read(bt);
                txta.setText(new String(bt));
                fis.close();
                setTitle("ADitor - "+fileName);
            }catch(Exception e2){}
        }
    }
    private static void printLines(String name, InputStream ins) throws Exception {
      String line = null;
      BufferedReader in = new BufferedReader(new InputStreamReader(ins));
      while ((line = in.readLine()) != null) {
          System.out.println(name + " " + line);
      }
    }
    public static void main(String[] args) throws Exception
    {
        ADitor a=new ADitor();
    }
}
