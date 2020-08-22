package teste;

import controller.UsuarioController;
import java.util.List;
import modelo.Usuario;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author valeria
 */
public class TesteUsuario extends TesteBase {

    UsuarioController uc = new UsuarioController();

    @Test
    public void t01_InserirUsuario() {

        Usuario usuario = new Usuario();

        usuario.setNome("Maria Joaquina");
        usuario.setEmail("mariajoaquina@gmail.com");
        usuario.setSenha("987654");

        usuario.setDdd(81);
        usuario.setNumero("97777-9999");
        usuario.setTipo("CELULAR");
        uc.setCadastro(usuario);

        uc.inserir();
        assertNotNull(usuario.getId());
    }

    @Test
    public void t02_InserirUsuario2() {

        Usuario usuario1 = new Usuario();

        usuario1.setNome("Lorenzo Pedro");
        usuario1.setEmail("lorenzo@gmail.com");
        usuario1.setSenha("123456");

        usuario1.setDdd(81);
        usuario1.setNumero("3333-9999");
        usuario1.setTipo("RESIDENCIAL");

        uc.setCadastro(usuario1);

        uc.inserir();
        assertNotNull(usuario1.getId());

    }

    @Test
    public void t03_AlterarTelefone() {
        List<Usuario> usuarios = uc.recuperarTodos();

        assertEquals(usuarios.size(), 2);

    }

    @Test
    public void t04() {
        List<Usuario> usuariosA = uc.recuperarTodos();
        Usuario uA[] = new Usuario[usuariosA.size()];

        for (int i = 0; i < usuariosA.size(); i++) {
            uA[i] = usuariosA.get(i);
        }
        Usuario selecionado = uA[1];
        selecionado.setEmail("joao@gmail.com");
        uc.setSelecionar(selecionado);
        uc.alterar();
        
        List<Usuario> usuariosB = uc.recuperarTodos();
        Usuario uB[] = new Usuario[usuariosB.size()];
        for (int i = 0; i < usuariosB.size(); i++) {
            uB[i] = usuariosB.get(i);
        }
        Usuario selecionadoB = uB[1];
        assertEquals("joao@gmail.com",selecionadoB.getEmail());
    }
    
    @Test
    public void t05_deletarUsuario(){
        List<Usuario> usuariosD = uc.recuperarTodos();
       
        Usuario u[] = new Usuario[usuariosD.size()];
        
        for (int i = 0; i < usuariosD.size(); i++) {
            u[i] = usuariosD.get(i);
        }
        Usuario selecionar = u[0];
        
       
        uc.setSelecionar(selecionar);
        uc.deletar();
        List<Usuario> usuariosDel = uc.recuperarTodos();
        boolean uD = usuariosDel.contains(selecionar);
        
        assertFalse(uD);
    }
}
