/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package luceneindexdemo;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hit;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
//import org.neo4j.jdbc.*;
import org.restlet.engine.util.StringUtils;


/**
 *
 * @author rishav12
 */
public class LuceneIndexDemo {

    /**
     */
    
    public static final String FILES_TO_INDEX="/home/rishav12/NetBeansProjects/LuceneIndexDemo/filestoIndex";
    public static final String INDEX_DIRECTORY="/home/rishav12/NetBeansProjects/LuceneIndexDemo/indexDirectory";
    public static final String FIELD_PATH="path";
    public static final String FIELD_CONTENTS="contents";
    public static  String user="Virat";
   public static JComboBox jString;
    public static JTextField txtField;
    public static JFrame jFrame;
    public static JButton button;
    public static JPanel jPanel;
    public static Connection con;
    public static void main(String[] args) {
        
       
        try {
            // TODO code application logic here
    //int a=10;            
//System.out.println("Enter your user name: ");
            //createIndex();
            con=DriverManager.getConnection("jdbc:neo4j://localhost:7474/");
            jFrame=new JFrame();
            jPanel=new JPanel();
            
            
            /*String query1="match (n:People) return n.name";
            
       
       Vector<String> l=new Vector<>();
       ResultSet rs=con.createStatement().executeQuery(query1);
            while(rs.next())
            {
                l.add(rs.getString("n.name"));
            }
            
            jString=new JComboBox((Vector) l);*/
       
       txtField=new JTextField(10);
       button=new JButton("post");
       button.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        String query=txtField.getText();
                        searchIndex(query);
                    } catch (Exception ex) {
                        Logger.getLogger(LuceneIndexDemo.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                }
           });
       jPanel.add(txtField);
   //    jPanel.add(jString);
       jPanel.add(button);
       jFrame.add(jPanel);
       jFrame.setSize(300,300);
       //jFrame.setVisible(true);
            //searchIndex("i want to get high");
            searchIndex("i am fine, just wanted to see a movie");
           // searchIndex("i want to go to school");
            //searchIndex("Teaching in schools is a good job");
            //searchIndex("Search for nearby colleges and schools");
            //searchIndex("i am trying to develop an interest towards teaching");
            //searchIndex("this dress is great can i get one?");
             //searchIndex("");
            //searchIndex("want to gift my date");
            searchIndex("want to buy new clothes");
            searchIndex("this new car interests me");
            searchIndex("party at its best gives relaxation");
            //searchIndex("i am tired");
            searchIndex("went for dinner with my brother with a movie");
            //searchIndex("i need rest and sleep");
            //searchIndex("this was the best date I had thankyou...");
            //searchIndex("i want to fix a get together with my family");
            //searchIndex("i want to try weeed once");
            //searchIndex("alcohol and friends with hot babes");
            //System.out.println("rishav"+10);
        } catch (Exception ex) {
            Logger.getLogger(LuceneIndexDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
    }
     
    
    public static void createIndex() throws Exception {
        Analyzer analyzer=new StandardAnalyzer();
        boolean recreateIndexIfExists=true;
        IndexWriter indexWriter=new IndexWriter(INDEX_DIRECTORY, analyzer, recreateIndexIfExists);
        File dir=new File(FILES_TO_INDEX);
        File[] files=dir.listFiles();
        for(File file:files)
        {
            Document document=new Document();
            String path=file.getCanonicalPath();
            document.add(new Field(FIELD_PATH,path,Field.Store.YES,Field.Index.UN_TOKENIZED,Field.TermVector.WITH_OFFSETS));
            Reader reader=new FileReader(file);
            document.add(new Field(FIELD_CONTENTS,reader));
            //System.out.println(document.getField(FIELD_PATH));
            //System.out.println(document.hashCode());
            indexWriter.addDocument(document);
        }
        
        indexWriter.optimize();
        indexWriter.close();
                
    }
    

    public static void searchIndex(String s) throws IOException, ParseException, SQLException, FileNotFoundException, org.json.simple.parser.ParseException {
     
        Directory directory= FSDirectory.getDirectory(INDEX_DIRECTORY);
        IndexReader reader=IndexReader.open(directory);
        IndexSearcher search=new IndexSearcher(reader);
        Analyzer analyzer=new StandardAnalyzer();
        
        QueryParser queryparser=new QueryParser(FIELD_CONTENTS, analyzer);
        Query query=queryparser.parse(s);
        Hits hits=search.search(query);
        Iterator<Hit> it=hits.iterator();
        //System.out.println("hits:"+hits.length());
        float f_score;
        List<String> names=new ArrayList<>();
        while(it.hasNext())
        {
            Hit hit=it.next();
            f_score=hit.getScore();
            
            //System.out.println(f_score);
            Document document=hit.getDocument();
            Field f=document.getField(FIELD_PATH);
                 
            //System.out.println(f.readerValue());
            //System.out.println(document.getValues(FIELD_PATH));
            String path=document.get(FIELD_PATH);
            System.out.println(f_score+" "+new File(path).getName());
            //System.out.println(document.getValues(path));
            Field con1=document.getField(FIELD_PATH);
            String name=new File(path).getName();
            String q="match (n:People)  , (b:"+name+") where n.name='Nitish'  create (n)-[k:posts{status:'"+s+"',score:"+f_score+"}]->(b)";
         con.createStatement().executeQuery(q);
            //System.out.println("hit:"+path+" "+hit.getId()+" "+con);
            names.add(new File(path).getName());
        }
        
        //ProcessBuilder pb=new ProcessBuilder();
        
FileReader fReader=new FileReader("/home/rishav12/NetBeansProjects/LuceneIndexDemo/inntell.json");
JSONParser jsParser=new JSONParser();
//System.out.println("This is an assumption that you belong to US");
FileReader freReader=new FileReader("/home/rishav12/NetBeansProjects/LuceneIndexDemo/location.json");
//Connection con=DriverManager.getConnection("jdbc:neo4j://localhost:7474/");
JSONObject jsonObj=(JSONObject) jsParser.parse(fReader);
JSONObject locObj=(JSONObject) jsParser.parse(freReader);
        
    for(String name:names)
    {
        JSONObject jsObj=(JSONObject) jsonObj.get(name);
      JSONArray reqmts=(JSONArray) jsObj.get("true");
        Iterator<JSONArray> iter=reqmts.iterator();
        
        
     JSONArray jsArray=(JSONArray) locObj.get("CHENNAI");
        String resQuery="START n=node:restaurant('withinDistance:["+jsArray.get(0)+","+jsArray.get(1)+",7.5]') where";
         int k=0;
         List<String> lis=new ArrayList<>();
         while (iter.hasNext()) {
                    lis.add("n.type="+"'"+iter.next()+"'");
                    if(k==0)
                        resQuery+=" "+lis.get(k);
                    else
                        resQuery+=" or "+lis.get(k);
                        k++;
                  }
         resQuery+=" return n.lat,n.lon,n.type";
         System.out.println(resQuery);
         ResultSet res=con.createStatement().executeQuery(resQuery);
         while(res.next())
         {
          System.out.println("located:"+res.getString("n.lat")+","+res.getString("n.lon")+" type:"+res.getString("n.type"));  
         }
      
        




    }
   
       
    
    }
    
}

