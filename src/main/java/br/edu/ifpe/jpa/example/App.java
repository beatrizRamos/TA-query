package br.edu.ifpe.jpa.example;

import br.edu.ifpe.jpa.example.entities.Blog;
import static com.querydsl.core.group.GroupBy.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;

import br.edu.ifpe.jpa.example.entities.Car;
import br.edu.ifpe.jpa.example.entities.Post;
import br.edu.ifpe.jpa.example.entities.QBlog;
import static br.edu.ifpe.jpa.example.entities.QBlog.blog;
import static br.edu.ifpe.jpa.example.entities.QPost.post;
import br.edu.ifpe.jpa.example.entities.QCar;
import br.edu.ifpe.jpa.example.entities.QPost;
import br.edu.ifpe.jpa.querydsl.EntityManagerHelper;

public class App {
	
	static EntityManagerHelper helper = EntityManagerHelper.getInstance();
	
        
	public static void main(String[] args) {
	}

	
	// 1. Imprima na tela todos os blogs que possuem o id maior que 10
	public void questaoUm() {
            helper.execute((query) -> {
                List<Blog> ids =
                query
                        .select(blog)
                        .from(blog)
                        .where(blog.identifier.gt(10))
                        .fetch();
                        System.out.println(ids);
            });
	}

	// 2. Imprima na tela a descrição do blog que possui o nome "dia a dia, bit a bit"
	public void questaoDois() {
            helper.execute((query) -> {
                List<String> dsc =
                query
                        .select(blog.description)
                        .from(blog)
                        .where(blog.name.eq("dia a dia, bit a bit"))
                        .fetch();
                        System.out.println(dsc);
            });
	}

	// 3. Imprima na tela as decrições dos 5 primeiros blogs criados (considerar o atributo creationDate)
	public void questaoTres() {
            helper.execute((query) -> {
                 List<String> dsc =
                query
                        .select(blog.description)
                        .from(blog)
                        .orderBy(blog.creationDate.asc())
                        .limit(5)
                        .fetch();
                 System.out.println(dsc);
            });

	}

	// 4. Imprima na tela o título e conteúdo de todos os posts do blog com título recebido como parâmetro, 
	//ordenados alfabeticamente pelo título do post
	public void questaoQuatro(String titulo) {
             helper.execute((query) -> {
                List<Tuple> dsc =
                query
                        .select(post.title, post.content)
                        .from(post)
                        .where(post.blog.name.eq(titulo))
                        .orderBy(post.title.asc())
                        .limit(5)
                        .fetch();
                 System.out.println(dsc);
            });
	}

	// 5. Imprima na tela o título do último post do blog com título "título"
	public void questaoCinco(String titulo) {
             helper.execute((query) -> {
                List<Tuple> dsc =
                query
                        .select(post.title, post.content)
                        .from(post)
                        .where(post.blog.name.eq(titulo))
                        .orderBy(post.title.asc())
                        .limit(5)
                        .fetch();
                 System.out.println(dsc);
            });
	}

	// 6. Retorne uma lista com os títulos de todos os posts publicados no blog com título tituloBlog 
	//entre o período dataInicial e dataFinal.
	public List<String> questaoSeis(Date dataInicial, Date dataFinal, String tituloBlog) {
            List<String> lista = new ArrayList<>() ;
	helper.execute((query) -> {
                lista.addAll(
                query
                        .select(post.title)
                        .from(post)
                        .where(post.blog.name.eq(tituloBlog).and(post.creationDate.after(dataInicial).and(post.creationDate.before(dataFinal))))
                        .fetch());
                 
            });	
            return lista;
	}

	// 7. Imprima na tela a média de posts existentes nos blogs
	public void questaoSete() {
          
	}
}
