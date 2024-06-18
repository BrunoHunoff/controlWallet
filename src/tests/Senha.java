public class Senha {

    public static void main(String[] args) {

        String senha = "1234";
        
        private String criptografarSenha(String senha) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] hash = md.digest(senha.getBytes());
                StringBuilder hexString = new StringBuilder(2 * hash.length);
                for (byte b : hash) {
                    String hex = Integer.toHexString(0xff & b);
                    if (hex.length() == 1) {
                        hexString.append('0');
                    }
                    hexString.append(hex);
                }
                return hexString.toString();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }

        String novaSenha = criptografarSenha(senha);

        System.out.println(novaSenha);

    }
    
}
