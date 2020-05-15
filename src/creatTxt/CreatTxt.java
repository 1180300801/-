package creatTxt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CreatTxt {

	public static void main(String[] args) throws IOException {
		File file = new File("test/txt/test_3.txt"); 
		file.createNewFile();
	}
	
	public static void creatTxt(String fileName) throws IOException {
		File f = new File(fileName);
		//按行读取
		BufferedReader br = new BufferedReader(new FileReader(f));
		File file = new File("src/txt/New"+fileName.substring(8)); 
		file.createNewFile();
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));  
		String read = "";
		int i = 0;
		while((read = br.readLine()) != null) {
			if(i == 13)
				i = 0;
			if(read.equals(""))
				i--;
			if(i == 0|i == 4|i == 5) {
				read = read.replaceFirst("-0[12]-", "-05-");
			}
			bw.write(read+"\n");
			i++;
		}
		br.close();
		bw.close();
	}
	
	public static void creatTrainTxt(String fileName,int k) throws IOException{
		File f = new File(fileName);
		//按行读取
		BufferedReader br = new BufferedReader(new FileReader(f));
		File file = new File("src/txt/TrainSchedule_"+k+".txt"); 
		file.createNewFile();
		BufferedWriter bw = new BufferedWriter(new FileWriter(file)); 
		String read = "";
		int i = 0;
		int j = 0;
		String[] type = new String[]{"A","B","C","D","E","F","G","H"};
		int[] personnelNumber = new int[]{50,60,70,80,90,100,110,120};
		int[] FactoryYear = new int[]{2012,2013,2014,2015,2016,2017,2018,2019};
		while((read = br.readLine()) != null) {
			if(i == 13)
				i = 0;
			if(read.equals(""))
				i--;
			switch(i) {
			case 0:
				read = read.replaceFirst("Flight", "Train");
				read = read.replaceFirst("\\,[A-Z]+", ",G");
				break;
			case 2:
				read = read.replaceFirst("DepartureAirport", "DepartureStation");
				read += "\nIntermediateStation:StationA,StationB,StationC,StationD";
				break;
			case 3:
				read = read.replaceFirst("ArrivalAirport", "ArrivalStation");
				break;
			case 6:
				read = read.replaceFirst("Plane", "Carriage");
				break;
			case 8:
				read = read.replaceFirst(":[A-Za-z0-9]+", ":"+type[j%8]);
				break;
			case 9:
				read = read.replaceFirst("Seats:[0-9]+", "PersonnelNumber:"+personnelNumber[j%8]);
				break;
			case 10:
				read = read.replaceFirst("Age:[0-9]+(\\.[0-9]+)?", "FactoryYear:"+FactoryYear[j%8]);
				break;
			}
			bw.write(read+"\n");
			i++;
		}
		br.close();
		bw.close();
	}
	
	public static String timeAddSeven(String time) {
		int month = Integer.parseInt(time.substring(6, 7));
		int day = Integer.parseInt(time.substring(8, 10));
		String temp = "";
		switch(month){
		case 2:
			if(day+7<=29&day+7>=10)
			    temp = time.replaceFirst("-02-[0-9]+", "-02-"+String.valueOf(day+7));
			else if(day+7>29)
				temp =time.replaceFirst("-02-[0-9]+", "-03-0"+String.valueOf((day+7)/29));
			else
				temp = time.replaceFirst("-02-[0-9]+", "-02-0"+String.valueOf(day+7));
			
			break;
		case 3:
			if(day+7<=31&day+7>=10)
			    temp = time.replaceFirst("-03-[0-9]+", "-03-"+String.valueOf(day+7));
			else if(day+7>31)
				temp =time.replaceFirst("-03-[0-9]+", "-04-0"+String.valueOf((day+7)/31));
			else
				temp = time.replaceFirst("-03-[0-9]+", "-03-0"+String.valueOf(day+7));
			break;
		case 4:
			if(day+7<=30&day+7>=10)
			    temp = time.replaceFirst("-04-[0-9]+", "-04-"+String.valueOf(day+7));
			else if(day+7>30)
				temp =time.replaceFirst("-04-[0-9]+", "-06-0"+String.valueOf((day+7)/30));
			else
				temp = time.replaceFirst("-04-[0-9]+", "-04-0"+String.valueOf(day+7));
			break;
		case 5:
			if(day+7<=31&day+7>=10)
			    temp = time.replaceFirst("-05-[0-9]+", "-05-"+String.valueOf(day+7));
			else if(day+7>31)
				temp =time.replaceFirst("-05-[0-9]+", "-06-0"+String.valueOf((day+7)/31));
			else
				temp = time.replaceFirst("-05-[0-9]+", "-05-0"+String.valueOf(day+7));
			break;
		case 6:
			if(day+7<=30&day+7>=10)
			    temp = time.replaceFirst("-06-[0-9]+", "-06-"+String.valueOf(day+7));
			else if(day+7>30)
				temp =time.replaceFirst("-06-[0-9]+", "-07-0"+String.valueOf((day+7)/30));
			else
				temp = time.replaceFirst("-06-[0-9]+", "-06-0"+String.valueOf(day+7));
			break;
		case 7:
			if(day+7<=31&day+7>=10)
			    temp = time.replaceFirst("-07-[0-9]+", "-07-"+String.valueOf(day+7));
			else if(day+7>31)
				temp =time.replaceFirst("-07-[0-9]+", "-08-0"+String.valueOf((day+7)/31));
			else
				temp = time.replaceFirst("-07-[0-9]+", "-07-0"+String.valueOf(day+7));
			break;
		}
		return temp;		
	}

	public static void creatClassTxt() throws IOException{
		File f = new File("src/txt/test.txt");
		//按行读取
		BufferedReader br = new BufferedReader(new FileReader(f));
		File file = new File("src/txt/ClassSchedule_1.txt"); 
		file.createNewFile();
		BufferedWriter bw = new BufferedWriter(new FileWriter(file)); 
		String read = "";
		String S = "";
		int i = 0;
		int sub = 0;
		String time = "";
		while((read = br.readLine()) != null) {
			i++;
			if(i == 1) {
				time = (read.split(":")[1]).split(",")[0];
			}
			if(i == 3) {
				sub = Integer.parseInt(read.split("-")[1])-Integer.parseInt(read.split("-")[0])+1;
				//System.out.println(sub);
			}
			else {
				S = S+read+"\n";
			}
			if(i == 13) {
				i = 0;
				for(int j = 0;j<sub;j++) {
					bw.write(S);
					time = timeAddSeven(time);
					//System.out.println(time);
					S = S.replaceAll("2020-0[0-9]-[0-3][0-9]", time);
				}
				sub = 0;
			}
		}
		br.close();
		bw.close();
	}
	
	public static void creatNewTxt(String flieName) throws IOException{
		File f = new File(flieName);
		//按行读取
		BufferedReader br = new BufferedReader(new FileReader(f));
		File file = new File("src/txt/test_1.txt"); 
		file.createNewFile();
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		String read = "";
		String S = "";
		int i = 0;
		String time = "";
		while((read = br.readLine()) != null) {
			if(read.equals(""))
				i--;
			i++;
			if(i == 1) {
				time = (read.split(":")[1]).split(",")[0];
			}
			S = S+read+"\n";
			if(i == 13) {
				i = 0;
				bw.write(S);
				time = timeAddFifteen(time);
				S = S.replaceAll("2020-05-[0-3][0-9]", time);
				bw.write(S);
				S = "";
			}
		}
		br.close();
		bw.close();
	}
	
	public static String timeAddFifteen(String time) {
		int month = Integer.parseInt(time.substring(6, 7));
		int day = Integer.parseInt(time.substring(8, 10));
		String temp = "";
		switch(month){
		case 2:
			if(day+7<=29&day+7>=10)
			    temp = time.replaceFirst("-02-[0-9]+", "-02-"+String.valueOf(day+7));
			else if(day+7>29)
				temp =time.replaceFirst("-02-[0-9]+", "-03-0"+String.valueOf((day+7)/29));
			else
				temp = time.replaceFirst("-02-[0-9]+", "-02-0"+String.valueOf(day+7));
			
			break;
		case 3:
			if(day+7<=31&day+7>=10)
			    temp = time.replaceFirst("-03-[0-9]+", "-03-"+String.valueOf(day+7));
			else if(day+7>31)
				temp =time.replaceFirst("-03-[0-9]+", "-04-0"+String.valueOf((day+7)/31));
			else
				temp = time.replaceFirst("-03-[0-9]+", "-03-0"+String.valueOf(day+7));
			break;
		case 4:
			if(day+7<=30&day+7>=10)
			    temp = time.replaceFirst("-04-[0-9]+", "-04-"+String.valueOf(day+7));
			else if(day+7>30)
				temp =time.replaceFirst("-04-[0-9]+", "-06-0"+String.valueOf((day+7)/30));
			else
				temp = time.replaceFirst("-04-[0-9]+", "-04-0"+String.valueOf(day+7));
			break;
		case 5:
			if(day+15<=31)
			    temp = time.replaceFirst("-05-[0-9]+", "-05-"+String.valueOf(day+15));
			else if(day+15>31)
				temp =time.replaceFirst("-05-[0-9]+", "-06-0"+String.valueOf((day+15)/31));
			break;
		case 6:
			if(day+7<=30&day+7>=10)
			    temp = time.replaceFirst("-06-[0-9]+", "-06-"+String.valueOf(day+7));
			else if(day+7>30)
				temp =time.replaceFirst("-06-[0-9]+", "-07-0"+String.valueOf((day+7)/30));
			else
				temp = time.replaceFirst("-06-[0-9]+", "-06-0"+String.valueOf(day+7));
			break;
		case 7:
			if(day+7<=31&day+7>=10)
			    temp = time.replaceFirst("-07-[0-9]+", "-07-"+String.valueOf(day+7));
			else if(day+7>31)
				temp =time.replaceFirst("-07-[0-9]+", "-08-0"+String.valueOf((day+7)/31));
			else
				temp = time.replaceFirst("-07-[0-9]+", "-07-0"+String.valueOf(day+7));
			break;
		}
		return temp;		
	}
}
