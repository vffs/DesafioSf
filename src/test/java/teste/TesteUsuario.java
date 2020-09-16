package teste;

import controller.UsuarioController;
import java.util.List;
import modelo.Usuario;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import persistencia.Persistencia;

/**
 *
 * @author valeria
 */
public class TesteUsuario extends TesteBase {

    UsuarioController uc;
    Usuario usuario;
    Usuario selecionado;
    List<Usuario> usuarios;
    List<Usuario> usuariosB;
    
    @Before
    public void inicializa() {
        this.uc = new UsuarioController();
        this.usuario = new Usuario();
    }
    
    @Test
    public void t01_InserirUsuario() {
        
        this.usuario.setNome("Maria Joaquina");
        this.usuario.setEmail("mariajoaquina@gmail.com");
        this.usuario.setSenha("987654");

        this.usuario.setDdd(81);
        this.usuario.setNumero("97777-9999");
        this.usuario.setTipo("CELULAR");
        
        Persistencia.getCurrentIntance().insert(this.usuario);
        assertNotNull(this.usuario.getId());
    }

    @Test
    public void t02_RetornaAQuantidadeDeUsuariosCadastrado() {
        this.usuarios = this.uc.recuperarTodos();
        
        assertEquals(this.usuarios.size(), 2);

    }

    @Test
    public void t03_AlterarEmail() {
        this.usuarios = this.uc.recuperarTodos();
        Usuario uA[] = new Usuario[this.usuarios.size()];

        for (int i = 0; i < this.usuarios.size(); i++) {
            uA[i] = this.usuarios.get(i);
        }
        this.selecionado = uA[1];
        this.selecionado.setEmail("joao@gmail.com");
        Persistencia.getCurrentIntance().update(this.selecionado);
        
        this.usuariosB = uc.recuperarTodos();
        Usuario uB[] = new Usuario[this.usuariosB.size()];
        for (int i = 0; i < this.usuariosB.size(); i++) {
            uB[i] = this.usuariosB.get(i);
        }
        Usuario selecionadoB = uB[1];
        assertEquals("joao@gmail.com", selecionadoB.getEmail());
    }
    
    @Test
    public void t04_DeletarUsuario(){
        this.usuarios = this.uc.recuperarTodos();
       
        Usuario u[] = new Usuario[this.usuarios.size()];
        
        for (int i = 0; i < this.usuarios.size(); i++) {
            u[i] = this.usuarios.get(i);
        }
        this.selecionado = u[0];
               
        Persistencia.getCurrentIntance().delete(this.selecionado);
        this.usuariosB = uc.recuperarTodos();
        boolean uD = usuariosB.contains(this.selecionado);
        
        assertFalse(uD);
    }
}
