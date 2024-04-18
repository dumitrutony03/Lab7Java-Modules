package org.example.repository.persoanaOficiu;

import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import org.example.models.PersoanaOficiu;
import org.example.repository.jdbc.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PersoanaOficiuRepository implements IPersoanaOficiuRepository {

    private final JdbcUtils dbUtils;
//    private final static Logger logger = LogManager.getLogger(PersoanaOficiuRepository.class);

    public PersoanaOficiuRepository(Properties props) {
//        logger.info("Initializing PersoanaOficiuRepository with properties: {} ", props);
        dbUtils = new JdbcUtils(props);
    }

    @Override
    public void Add(PersoanaOficiu persoanaOficiu) {
        Connection conection = dbUtils.getConnection();
//        logger.traceEntry("ADD task {}", persoanaOficiu);


        try {
//            logger.traceEntry("ADD task {}", persoanaOficiu);

            try (PreparedStatement preStmt = conection.prepareStatement("INSERT INTO PersoanaOficiu (nume, parola) VALUES (?,?)")) {
                preStmt.setString(1, persoanaOficiu.getNume());
                preStmt.setString(2, persoanaOficiu.getParola());

                int result = preStmt.executeUpdate(); // executam query-ul

//                logger.trace("ADDED {} persoana oficiu", result);
            }
        } catch (SQLException ex) {
//            logger.error(ex);
            System.err.println("Err DB " + ex);
        }
//        logger.traceExit();
    }

    @Override
    public void Delete(Integer id) throws NotImplementedException {
        throw new NotImplementedException("Metoda nu este implementata");
    }

    @Override
    public void Update(Integer id, PersoanaOficiu persoanaOficiu) throws NotImplementedException {
        throw new NotImplementedException("Metoda nu este implementata");
    }

    @Override
    public PersoanaOficiu FindById(Integer id) throws NotImplementedException {
        throw new NotImplementedException("Metoda nu este implementata");
    }

    @Override
    public List<PersoanaOficiu> FindAll() {

        /// LEFT TO BE IMPLEMENTED
        Connection con = dbUtils.getConnection();

        System.out.println(
                "Luam 'persoanele oficiu' din baza de date, PersoanaOficiu.db"
        );
        List<PersoanaOficiu> persoanaOficiuLista = new ArrayList<>();

        try {
//            logger.traceEntry("FindAll task");
            try (PreparedStatement statement = con.prepareStatement("SELECT * from PersoanaOficiu");
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    PersoanaOficiu persoanaOficiu = new PersoanaOficiu(resultSet.getString("nume"), resultSet.getString("parola"));
                    persoanaOficiu.SetId(id);

                    persoanaOficiuLista.add(persoanaOficiu);
                }
            }
        } catch (SQLException ex) {
//            logger.error(ex);
            System.err.println("Err DB " + ex);
        }
//        logger.traceExit();
        return persoanaOficiuLista;
    }

    @Override
    public PersoanaOficiu findByNumeAndParola(String nume, String parola) {
        System.out.println("findByNumeAndParola din PersoanaOficiuRepository");

//        logger.info("Metoda destinata cautarii obiectului PersoanaOficiu dupa nume si parola", nume, parola);
        String sql = "SELECT * FROM PersoanaOficiu WHERE nume = ? AND parola = ?";

        try (Connection conn = dbUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nume);
            pstmt.setString(2, parola);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                PersoanaOficiu persoanaOficiu = new PersoanaOficiu(nume, parola);
                persoanaOficiu.SetId(rs.getInt("id"));
                System.out.println("Afisam detaliile persoaneiOficiu cautate dupa nume + parola: " + persoanaOficiu.toString());

                return persoanaOficiu;
            }
            else{
                System.out.println("Nu exista obiectul PersoanaOficiu dorit.");
//                logger.error("Nu exista obiectul PersoanaOficiu dorit.");
                return null;
            }
        } catch (SQLException e) {
//            logger.error("Eroare la serverul SQLite", e.getMessage());
            System.out.println(e.getMessage());
            return null;
        }
    }
}
