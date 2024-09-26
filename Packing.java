import java.util.*;
	import java.io.*;

	public class PackingAct {
	    public static void main(String[] arg) throws Exception {
	        System.out.println("------------------------------------------------");
	        System.out.println("---------- Marvellous Packer Unpacker ----------");
	        System.out.println("------------------------------------------------");

	        Scanner sobj = new Scanner(System.in);

	        System.out.println("Enter the name of Directory which contains all files that you want to pack:");
	        String directoryName = sobj.nextLine();

	        System.out.println("Enter the name of packed file that you want to create:");
	        String packedFileName = sobj.nextLine();

	        File packedFile = new File(packedFileName);
	        if (!packedFile.createNewFile()) {
	            System.out.println("Unable to create packed file...");
	            sobj.close();
	            return;
	        }

	        System.out.println("Packed file successfully created.");
	        File directory = new File(directoryName);

	        if (directory.isDirectory()) {
	            File[] files = directory.listFiles();
	            if (files != null) {
	                System.out.println("Number of files in the directory: " + files.length);

	                try (FileOutputStream fos = new FileOutputStream(packedFile)) {
	                    byte[] buffer = new byte[1024];
	                    int bytesRead;

	                    System.out.println("Packing activity started...");
	                    for (File file : files) {
	                        // Create header with file name and file size
	                        String header = String.format("%-100s", file.getName() + " " + file.length());

	                        // Write header to the packed file
	                        fos.write(header.getBytes());

	                        // Write file contents to packed file
	                        try (FileInputStream fis = new FileInputStream(file)) {
	                            while ((bytesRead = fis.read(buffer)) != -1) {
	                                fos.write(buffer, 0, bytesRead);
	                            }
	                        }

	                        System.out.println("File packed: " + file.getName());
	                    }

	                    System.out.println("Packing activity completed.");
	                    System.out.println("Total files packed successfully: " + files.length);
	                }
	            } else {
	                System.out.println("No files found in the directory.");
	            }
	        } else {
	            System.out.println("Directory does not exist.");
	        }

	        sobj.close();
	    }
	}



