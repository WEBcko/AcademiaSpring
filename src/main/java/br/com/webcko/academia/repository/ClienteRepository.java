package br.com.webcko.academia.repository;

import br.com.webcko.academia.entity.Cliente;
import br.com.webcko.academia.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    //public List<Cliente> findByNome(final String nome);

//    //buscando da forma customizada
//    @Query("from Modelo whre nome like :nome")
//    public  List<Cliente> findByNomeLike (@Param("nome") final String nome);
//
//    //buscando da forma nativa customizada
//    @Query(value = "select * from modelos where nome like :nome", nativeQuery = true)
//    public  List<Cliente> findByNomeLikeNative (@Param("nome") final String nome);


    @Query("from Cliente where ativo = true")
    public List<Cliente> findByAtivos ();

    @Query("from Cliente where cpf = :cpf")
    public List<Cliente> findCpf (@Param("cpf") final String cpf);

    @Query("from Cliente where cpf = :cpf and id not in (:id)")
    public List<Cliente> findCpfId (@Param("cpf") final String cpf,@Param("id") final Long id);

    @Query("from Cliente where telefone = :telefone")
    public List<Cliente> findTelefone (@Param("telefone") final String telefone);
}
