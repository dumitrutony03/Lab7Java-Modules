package org.example.repository.participant;

import jdk.jshell.spi.ExecutionControl;
import org.example.models.CapacitateMotor;
import org.example.models.Cursa;
import org.example.models.Echipa;
import org.example.models.Participant;
import org.example.repository.jdbc.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ParticipantRepository implements IParticipantRepository {

    private JdbcUtils dbUtils;

    //    private static final Logger logger= LogManager.getLogger();
//    private final static Logger logger = LogManager.getLogger(PersoanaOficiuRepository.class);

    public ParticipantRepository(Properties props) {
//        logger.info("Initializing ParticipantRepository with properties: {} ", props);
        dbUtils = new JdbcUtils(props);
    }

    @Override
    public void Add(Participant participant) {
        Connection conection = dbUtils.getConnection();

        try {
//            logger.traceEntry("ADD task {}", participant);

            try (PreparedStatement preStmt = conection.prepareStatement("INSERT INTO Participant (nume, echipa, id_cursa, capMotor) VALUES (?,?,?,?)")) {
                preStmt.setString(1, participant.GetNumeParticipant());

                String echipa = String.valueOf(participant.GetEchipa());
                preStmt.setObject(2, echipa);

                Integer id_cursa = participant.GetCursa().GetId();
                String capMotor = String.valueOf(participant.GetCursa().GetCapMotor());
                preStmt.setInt(3, id_cursa);
                preStmt.setObject(4, capMotor);

                int result = preStmt.executeUpdate(); // executam query-ul

//                logger.trace("ADDED {} participant", result);
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
    public void Update(Integer id, Participant participant) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Metoda nu este implementata");
    }

    @Override
    public Participant FindById(Integer id) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Metoda nu este implementata");
    }

    @Override
    public List<Participant> FindAll() {
        Connection con = dbUtils.getConnection();

        System.out.println(
                "Luam 'PARTICIPANTII' din baza de date, Participant.db"
        );
        List<Participant> participantLista = new ArrayList<>();

        try {
//            logger.traceEntry("FindAll task");
            try (PreparedStatement statement = con.prepareStatement("SELECT * from Participant");
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");

                    String nume = resultSet.getString("nume");
                    Echipa echipa = Echipa.valueOf(resultSet.getString("echipa"));

                    Integer cursa_id = resultSet.getInt("id_cursa");
                    CapacitateMotor capMotor = CapacitateMotor.valueOf(resultSet.getString("capMotor"));
                    Cursa cursa = new Cursa(capMotor);
                    cursa.SetId(cursa_id);

                    Participant participant = new Participant(nume, echipa, cursa);
                    participant.SetId(id);

                    participantLista.add(participant);
                }
            }
        } catch (SQLException ex) {
//            logger.error(ex);
            System.err.println("Err DB " + ex);
        }
//        logger.traceExit();
        return participantLista;
    }
}
