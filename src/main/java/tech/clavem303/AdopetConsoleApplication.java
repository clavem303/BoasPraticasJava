package tech.clavem303;

import tech.clavem303.service.PetService;
import tech.clavem303.service.ShelterService;
import java.util.Scanner;

public class AdopetConsoleApplication {

    public static void main(String[] args) {
        ShelterService shelterService = new ShelterService();
        PetService petService = new PetService();

        System.out.println("##### BOAS VINDAS AO SISTEMA ADOPET CONSOLE #####");
        try {
            int opcaoEscolhida = 0;
            while (opcaoEscolhida != 5) {
                System.out.println("\nDIGITE O NÚMERO DA OPERAÇÃO DESEJADA:");
                System.out.println("1 -> Listar abrigos cadastrados");
                System.out.println("2 -> Cadastrar novo abrigo");
                System.out.println("3 -> Listar pets do abrigo");
                System.out.println("4 -> Importar pets para abrigo");
                System.out.println("5 -> Sair");

                String textoDigitado = new Scanner(System.in).nextLine();
                opcaoEscolhida = Integer.parseInt(textoDigitado);

                if (opcaoEscolhida == 1) {
                    shelterService.listShelters();
                } else if (opcaoEscolhida == 2) {
                    shelterService.registerShelter();
                } else if (opcaoEscolhida == 3) {
                   petService.listPetsShelter();
                } else if (opcaoEscolhida == 4) {
                   petService.importPetsToShelter();
                } else if (opcaoEscolhida == 5) {
                    break;
                } else {
                    System.out.println("NÚMERO INVÁLIDO!");
                    opcaoEscolhida = 0;
                }
            }
            System.out.println("Finalizando o programa...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
