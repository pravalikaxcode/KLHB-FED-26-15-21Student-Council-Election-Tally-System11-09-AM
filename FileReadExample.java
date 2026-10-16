import java.io.*;
class FileReadExample {
public static void main(String[] args) {
try {
BufferedReader reader = new BufferedReader(new FileReader("parcels.txt"));

String line;
while((line = reader.readLine())!= null){
 System.out.println(line);
}
reader.close();

}catch(IOException e) {
System.out.println("File error:"+e.getMessage());
}
}
}