package org.example.repository.cursa;

import jdk.jshell.spi.ExecutionControl;
import org.example.models.CapacitateMotor;
import org.example.models.Cursa;
import org.example.repository.jdbc.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CursaRepository implements ICursaRepository {
    private JdbcUtils dbUtils;

    //    private static final Logger logger= LogManager.getLogger();
//    private final static Logger logger = LogManager.getLogger(PersoanaOficiuRepository.class);

    public CursaRepository(Properties props) {
//        logger.info("Initializing CursaRepository with properties: {} ", props);
        dbUtils = new JdbcUtils(props);
    }

    @Override
    public void Add(Cursa cursa) {
        Connection conection = dbUtils.getConnection();

        try {
//            logger.traceEntry("ADD task {}", cursa);

            try (PreparedStatement preStmt = conection.prepareStatement("INSERT INTO Cursa (capMotor) VALUES (?)")) {
                String capMotor = String.valueOf(cursa.GetCapMotor());
                preStmt.setString(1, capMotor);

                int result = preStmt.executeUpdate(); // executam query-ul

//                logger.trace("ADDED {} cursa", result);
            }
        } catch (SQLException ex) {
//            logger.error(ex);
            System.err.println("Err DB " + ex);
        }
//        logger.traceExit();
    }

    @Override
    public void Delete(Integer id) throws ExecutionControl.NotImplementedException {

        throw new ExecutionControl.NotImplementedException("Metoda nu este implementata");
    }

    @Override
    public void Update(Integer id, Cursa cursa) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Metoda nu este implementata");
    }

    @Override
    public Cursa FindById(Integer id) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Metoda nu este implementata");
    }

    @Override
    public List<Cursa> FindAll() {

        Connection con = dbUtils.getConnection();

        System.out.println(
                "Luam 'CURSELE' din baza de date, Participant.db"
        );
        List<Cursa> participantLista = new ArrayList<>();

        try {
//            logger.traceEntry("FindAll task");
            try (PreparedStatement statement = con.prepareStatement("SELECT * from Cursa");
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    CapacitateMotor capMotor = CapacitateMotor.valueOf(resultSet.getString("capMotor"));

                    Cursa cursa = new Cursa(capMotor);
                    cursa.SetId(id);
                    participantLista.add(cursa);
                }
            }
        } catch (SQLException ex) {
//            logger.error(ex);
            System.err.println("Err DB " + ex);
        }
//        logger.traceExit();
        return participantLista;
    }


    public Cursa findCursaByCapMotor(String capMotor) {System.out.println("findByNumeAndParola din PersoanaOficiuRepository");

//        logger.info("Metoda destinata cautarii obiectului Cursa dupa capMotor", capMotor);
        String sql = "SELECT * FROM Cursa WHERE capMotor = ?";

        try (Connection conn = dbUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, capMotor);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Cursa cursa = new Cursa(CapacitateMotor.valueOf(capMotor));
                cursa.SetId(rs.getInt("id"));
                System.out.println("Am gasit cursa cautata dupa capMotor");
                return cursa;
            }
            else{
                System.out.println("Nu am gasit cursa cautata dupa capMotor dorita.");
//                logger.error("Nu am gasit cursa cautata dupa capMotor dorita.");
                return null;
            }
        } catch (SQLException e) {
//            logger.error("Eroare la serverul SQLite", e.getMessage());
            System.out.println(e.getMessage());
            return null;
        }
    }
}


