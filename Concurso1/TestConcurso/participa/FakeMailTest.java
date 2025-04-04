package participa;

public class FakeMailTest implements Mail{
	   private String mail;

	@Override
	    public void enviarCorreo(String destinatario, String asunto, String mensaje) {
	        this.mail = destinatario + " - " + asunto + ": " + mensaje;
	    }

	    public String getMail() {
	        return this.mail;
	    }

	    public boolean noSeEnvioElCorreo() {
	        return this.mail == null;
	    }
	}

