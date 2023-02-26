package diplom.try1.CrudRepository;

import diplom.try1.Model.Report;
import diplom.try1.Model.Teachers;
import diplom.try1.Model.all_data;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrudAllData extends CrudRepository<all_data,Long> {
    Iterable<all_data> findAllBySemestr(double semestr);
    Iterable<all_data> findAllByTeachersAndSemestr(Teachers teachers, double semestr);
    Iterable<all_data> findAllBySemestrOrderByTeachers(double semestr);

    @Query(value =
            "SELECT tr.`name` AS name, SUM(ad.`N`) AS N, SUM(ad.`Л х Р`) AS LL_P,  SUM(ad.`Л х РК`) AS LL_PK,  SUM(ad.`П х ГП`) AS PP_GGPP,  \n" +
                    "SUM(ad.`П х ГКП`) AS PP_GGKPP,  SUM(ad.`L х ГL`) AS L_GGL,  SUM(ad.`L х ГКL`) AS L_GGKL,  SUM(ad.`іх_гх0,25`) AS I_GG_025,  \n" +
                    "SUM(ad.`іх_гх0,26`) AS I_GG_026, SUM(ad.`0_33х_dх(б+бк)`) AS E_BBBBK_033,  SUM(ad.`0_33х_dх(к+кк)`) AS E_KKK_033,  \n" +
                    "SUM(ad.`Заліки,+AL10`) AS zaliki,  SUM(ad.`2х_гхz`) AS GG_Z_2,  SUM(ad.`0_25х_мх(б+бк)`) AS M_025_BBBBK,  \n" +
                    "SUM(ad.`0_25х_мх(к+кк)`) AS M_025_KKK,  SUM(ad.`Qх(Б+БК)`) AS Q_BBBBK,  SUM(ad.`Qх(К+КК)`) AS Q_KKK, \n" +
                    "SUM(ad.`Gх(Б+БК)`) AS G_BBBBK,  SUM(ad.`Gх(К+КК)`) AS G_KKK,  SUM(ad.`0_5х_rх(б+бк)`) AS R_BBBBK_05,  \n" +
                    "SUM(ad.`0_5х_rх(к+кк)`) AS R_KKK_05,  SUM(ad.`0_33х_dх(б+бк)`) AS D_BBBBK_033,  SUM(ad.`0_33х_dх(к+кк)`) AS D_KKK_033,\n" +
                    "SUM(ad.`0_25х_fх(б+бк)`) AS BBBBK_F_025,  SUM(ad.`0_25х_fх(к+кк)`) AS KKK_F_025,  \n" +
                    "SUM(ad.`2 х e х г + 0,06 х n х (б+бк)/25`) AS E_GG_2_006_N_BBBBK_25,  SUM(ad.`2х_ехгк+0,06х_nх(к+кк)/25`) AS E_2_GGK_006_N_KKK_25, SUM(ad.`Всього годин`) AS `all`, SUM(ad.`Всього годин 2`) AS `all2`\n" +
                    "FROM `all_data` ad\n" +
                    "INNER JOIN `teachers` tr\n" +
                    "ON ad.`teachers_id` = tr.`id`\n" +
                    "GROUP BY ad.`teachers_id`"
            , nativeQuery = true
    )
    List<ReportInterface> findGroupByReportWithJPQL();

}
