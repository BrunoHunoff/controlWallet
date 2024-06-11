import java.util.Scanner;

import controllers.GerenciarUsuario;

public class Main {
    public static void main(String[] args) {
        GerenciarUsuario userManager = new GerenciarUsuario();
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("1. Criar Usuário");
            System.out.println("2. Listar Usuários");
            System.out.println("3. Atualizar Usuário");
            System.out.println("4. Deletar Usuário");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    System.out.print("Digite o username: ");
                    String username = scanner.nextLine();
                    System.out.print("Digite a senha: ");
                    String password = scanner.nextLine();
                    userManager.createUser(username, password);
                    break;
                case 2:
                    for (User user : userManager.readUsers()) {
                        System.out.println(user);
                    }
                    break;
                case 3:
                    System.out.print("Digite o ID do usuário: ");
                    String idToUpdate = scanner.nextLine();
                    System.out.print("Digite o novo username: ");
                    String newUsername = scanner.nextLine();
                    System.out.print("Digite a nova senha: ");
                    String newPassword = scanner.nextLine();
                    userManager.updateUser(idToUpdate, newUsername, newPassword);
                    break;
                case 4:
                    System.out.print("Digite o ID do usuário: ");
                    String idToDelete = scanner.nextLine();
                    userManager.deleteUser(idToDelete);
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (option != 5);

        scanner.close();
    }
}
