/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author CLAUDIO
 */
public class FuncionarioDAO extends ExecuteSQL{
    
    public FuncionarioDAO(Connection con){
        super(con);
    }
    
    public String Inserir_Funcionario(Funcionario f){
        String sql = "INSERT INTO funcionario VALUES (0,?,?,?)";
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            
            ps.setString(1, f.getNome());
            ps.setString(2, f.getLogin());
            ps.setString(3, f.getSenha());
            
            if(ps.executeUpdate() > 0){
                return "Funcionario Cadastrado com Sucesso!";
            }else{
                return "Erro ao Cadastrar Funcionario!";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    
    public List<Funcionario> Listar_Funcionario(){
        String sql = "SELECT codigo,nome,login,senha FROM funcionario";
        List<Funcionario> lista = new ArrayList<Funcionario>();
        
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                    Funcionario f = new Funcionario();
                    f.setCod(rs.getInt(1));
                    f.setNome(rs.getString(2));
                    f.setLogin(rs.getString(3));
                    f.setSenha(rs.getString(4));
                    
                    lista.add(f);
                }
            return lista;
            }else{
                return null;
            }
        } catch (Exception e) {
            return null;
        }
        
    }
    
    public List<Funcionario> Pesquisar_Nome_Funcionario(String nome){
        String sql = "SELECT codigo, nome, login, senha FROM funcionario WHERE nome LIKE '" + nome + "%'";
        List<Funcionario> lista = new ArrayList<Funcionario>();
        
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                    Funcionario f = new Funcionario();
                    f.setCod(rs.getInt(1));
                    f.setNome(rs.getString(2));
                    f.setLogin(rs.getString(3));
                    f.setSenha(rs.getString(4));
                    
                    lista.add(f);
                }
            return lista;
            }else{
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<Funcionario> Pesquisar_Cod_Funcionario(int cod){
        String sql = "SELECT codigo, nome, login, senha FROM funcionario WHERE codigo = '" + cod + "'" ;
        List<Funcionario> lista = new ArrayList<Funcionario>();
        
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                    Funcionario f = new Funcionario();
                    f.setCod(rs.getInt(1));
                    f.setNome(rs.getString(2));
                    f.setLogin(rs.getString(3));
                    f.setSenha(rs.getString(4));
                    
                    lista.add(f);
                }
            return lista;
            }else{
                return null;
            }
        } catch (Exception e){
            return null;
        }
    }
    

    
    public Funcionario Consulta_Funcionario(int cod){
        
         Funcionario f = new Funcionario();
         
        try {
            
            String sql = "SELECT * FROM funcionario WHERE codigo =  " + cod + "";
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
           
            
            if(rs != null){
                while(rs.next()){
                                      
                    f.setCod(rs.getInt(1));
                    f.setNome(rs.getString(2));
                    f.setLogin(rs.getString(3));
                    f.setSenha(rs.getString(4));
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        if(f.getCod() == cod){
            JOptionPane.showMessageDialog(null, "Funcionário encontrado com sucesso!");
        }else{
        JOptionPane.showMessageDialog(null, "Funcionário Não encontrado com sucesso!");    
        }
        return f;
    }
    
    
    
    
    public void Alterar_Funcionario(Funcionario f){
        String sql = "UPDATE funcionario SET nome = ?, login = ?, senha = ?"
                + "WHERE codigo = ?";
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setString(1, f.getNome());
            ps.setString(2, f.getLogin());
            ps.setString(3, f.getSenha());
            ps.setString(4, "" + f.getCod());
            
            if(ps.executeUpdate() > 0){
                JOptionPane.showMessageDialog(null,"Funcionario Atualizado com Sucesso!");
            }else{
                JOptionPane.showMessageDialog(null,"Erro ao Atualizar o Funcionário!");
            }
        } catch (Exception e) {
           e.getMessage();
        }
    }
    
    public List<Funcionario> ListaComboFuncionario(){
        String sql = "SELECT nome FROM funcionario ORDER BY nome";
        List<Funcionario> lista = new ArrayList<Funcionario>();
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                    Funcionario f = new Funcionario();
                    f.setNome(rs.getString(1));
                    lista.add(f);
                }
                return lista;
            }else{
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
    
    public String Excluir_Funcionario(Funcionario f){
        String sql = "DELETE FROM funcionario WHERE codigo = ?";
    
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setInt(1, f.getCod());
            
            if(ps.executeUpdate() > 0){
                return "Funcionário Excluído com Sucesso!";
            }else{
                return "Erro ao Excluir!";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    
    }
}
