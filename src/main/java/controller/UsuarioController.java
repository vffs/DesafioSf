package controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.Usuario;
import persistencia.Persistencia;

/**
 *
 * @author valeria
 */

@ManagedBean(name = "usuarioBean")
@SessionScoped
public class UsuarioController {

    private Usuario cadastro;
    private Usuario selecionar;

    @PostConstruct
    public void init() {
        this.cadastro = new Usuario();
    }

    public String inserir() {
        Persistencia.getCurrentIntance().insert(this.cadastro);
        this.cadastro = new Usuario();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuário cadastrado com sucesso!"));
        return "listarUsuario.xhtml";
    }
    
    public String alterar() {
        Persistencia.getCurrentIntance().update(this.selecionar);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuário alterado com sucesso!"));
        return "listarUsuario.xhtml";
    }

    public List<Usuario> recuperarTodos() {
        return Persistencia.getCurrentIntance().read("Select u from Usuario u", Usuario.class);

    }

    public void deletar() {
        Persistencia.getCurrentIntance().delete(this.selecionar);
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage("Feito", "Usuário deletado com sucesso!"));

    }

    public Usuario getCadastro() {
        return this.cadastro;
    }

    public void setCadastro(Usuario cadastro) {
        this.cadastro = cadastro;
    }

    public Usuario getSelecionar() {
        return this.selecionar;
    }

    public void setSelecionar(Usuario selecionar) {
        this.selecionar = selecionar;
    }

}
