import Demo.PrinterPrx;
import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.ObjectPrx;
import com.zeroc.Ice.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.Inet4Address;
import java.util.UUID;

public class Client {
    public static void main(String[] args) {

        try (Communicator communicator = Util.initialize(args, "client.cfg")) {
            ObjectPrx base = communicator.propertyToProxy("Service.Proxy");
            PrinterPrx printer = PrinterPrx.checkedCast(base);
            if (printer == null) {
                throw new Error("Invalid proxy");
            }
            try {

                String hostname = Inet4Address.getLocalHost().getHostName();

                if(args.length == 0){
                    System.out.println("Debe ingresar al menos un numero entero positivo y opcionalmente un nombre de archivo de texto local \n" +
                            "Este software ha sido creado por los siguientes autores:\n" +
                            "Angelica Corrales Quevedo - A00367954\n" +
                            "Santiago Trochez Velasco - A00369326 \n");

                }else {
                    int number = validateNumber(args[0]);

                    //System.out.println("\u001B[32m"+guid); --> Coloca el texto verde
                    if(number > 1){
                        String guid="";
                        if(args.length == 1){
                            guid = UUID.randomUUID().toString();
                            printer.printString(hostname + " says: " + "Hello World");

                            System.out.println(guid);

                        }else{
                            try{
                                FileReader fr = new FileReader("./"+args[1]);
                                BufferedReader br = new BufferedReader(fr);
                                guid = br.readLine();

                                System.out.println(guid);


                            }catch (FileNotFoundException fne){
                                System.out.println("El archivo no fue encontrado");
                            }
                        }


                        if(!guid.isEmpty()){

                        }


                    } else if (number == 0) {
                        System.out.println("El numero debe ser mayor a 1");

                    }else{
                        System.out.println("Debe ingresar un numero como primer parametro");
                    }
                }

            } catch (Exception e) {
                // TODO: handle exception
            }
        }

    }

    public static int validateNumber(String number){
        int validateNUmber = -1;

        try{
            validateNUmber = Integer.parseInt(number);

            if (validateNUmber > 1){
                validateNUmber = 2;

            }else{
                validateNUmber = 0;
            }

        }catch(NumberFormatException nfe){
            validateNUmber = -2;
        }

        return validateNUmber;
    }
}