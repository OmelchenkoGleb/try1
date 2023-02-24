package diplom.try1.DAO;

import diplom.try1.CrudRepository.CrudAllData;
import diplom.try1.CrudRepository.CrudShablon;
import diplom.try1.CrudRepository.CrudTeachers;
import diplom.try1.Model.Teachers;
import diplom.try1.Model.all_data;
import diplom.try1.Model.shablon;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class BdDAO {

    @Autowired
    CrudAllData crudAllData;
    @Autowired
    CrudTeachers crudTeachers;
    @Autowired
    CrudShablon crudShablon;

    public ArrayList<shablon> isShablon(){
        return (ArrayList<shablon>) crudShablon.findAll();
    }
    public void savedatalist(ArrayList<all_data> datalist){
        crudAllData.saveAll(datalist);
    }
    public void savedataTeacherslist(ArrayList<Teachers> datalist){
        crudTeachers.saveAll(datalist);
    }
    public Iterable getAllData(){return crudAllData.findAll();}
    public Iterable getSemestr(double semestr){return crudAllData.findAllBySemestrOrderByTeachers(semestr);}
    public Iterable getSemestrAndNullTeacher(Teachers teacher, double semestr){return crudAllData.findAllByTeachersAndSemestr(teacher,semestr);}
    public Iterable getSemestrAndTeacher(Long teacher, double semestr){return crudAllData.findAllByTeachersAndSemestr(crudTeachers.findById(teacher).get(),semestr);}
    public List<all_data> getSemestrAndTeacherList(Long teacher, double semestr){return (List<all_data>) crudAllData.findAllByTeachersAndSemestr(crudTeachers.findById(teacher).get(),semestr);}
    public Iterable getTeachers(){return crudTeachers.findAll();}
    public void updateAllData(Long idalldate, Long idteachers) {
        all_data allData = crudAllData.findById(idalldate).get();
        Teachers teacher = crudTeachers.findById(idteachers).get();
        allData.setTeachers(teacher);
        crudAllData.save(allData);
    }
    public void deleteAllData() {
        crudAllData.deleteAll();
    }
    public void deleteAllTeachers() {
        crudTeachers.deleteAll();
    }
    public void deleteOneData(Long id) {
        crudAllData.deleteById(id);
    }
    public void deleteOneTeacher(Long id) {
        crudTeachers.deleteById(id);
    }
    public Teachers findOneTeacher(Long id) {
        return crudTeachers.findById(id).get();
    }
    public all_data findOneData(Long id) {
        return crudAllData.findById(id).get();
    }
    public void updateTeacher(Teachers teachers) {
        crudTeachers.save(teachers);
    }
    public void saveShablon(MultipartFile file) throws IOException {
        shablon shablon = new shablon();
        shablon.setName(file.getOriginalFilename());
        shablon.setBytes(file.getBytes());
        crudShablon.save(shablon);
    }
    public void saveData(all_data allData) {
        crudAllData.save(allData);
    }
    public void updateOneData(all_data allData, Long idteachers, String type) {
        Teachers teacher = crudTeachers.findById(idteachers).get();
        allData.setTeachers(teacher);
        if (type.equals("1")) {
            //ОЧНИК
            if (allData.getE() == 1) allData.setN(allData.getObsyag()-30) ; else allData.setN(allData.getObsyag());

            float LL_P = allData.getP()*allData.getL();
            if (LL_P != 0) allData.setLL_P(LL_P);

            float LL_PK = allData.getLL()*allData.getPK();
            if (LL_PK != 0) allData.setLL_PK(0);

            float PP_GGPP = allData.getPP()*allData.getGGPP();
            if (PP_GGPP != 0) allData.setPP_GGPP(0);

            float PP_GGKPP = allData.getPP()*allData.getGGKPP();
            if (PP_GGKPP != 0) allData.setPP_GGKPP(0);

            float L_GGL = allData.getL()*allData.getL_GGL();
            if (L_GGL != 0) allData.setL_GGL(L_GGL);

            float L_GGKL = allData.getL()*allData.getGGKL();
            if (L_GGKL != 0) allData.setL_GGKL(L_GGKL);

            float I_GG_025 = allData.getI()*allData.getGG()*0.25f;
            if (I_GG_025 != 0) allData.setI_GG_025(I_GG_025);

            float I_GG_026 = allData.getI()*allData.getGG()*0.26f;
            if (I_GG_026 != 0) allData.setI_GG_026(I_GG_026);

            float E_BBBBK_033 = 0.33f*allData.getE()*(allData.getBB()+allData.getBBk());
            if (E_BBBBK_033 != 0) allData.setE_BBBBK_033(E_BBBBK_033);

            float E_KKK_033 = 0;
            if (E_KKK_033 != 0) allData.setE_KKK_033(E_KKK_033);

            float Zaliki = 0;
            if (Zaliki != 0) allData.setZaliki(Zaliki);

            float GG_Z_2 = 0;
            if (GG_Z_2 != 0) allData.setGG_Z_2(GG_Z_2);

            float M_025_BBBBK = 0;
            if (M_025_BBBBK != 0) allData.setM_025_BBBBK(M_025_BBBBK);

            float M_025_KKK = 0;
            if (M_025_KKK != 0) allData.setM_025_KKK(M_025_KKK);

            float Q_BBBBK = 0;
            if (Q_BBBBK != 0) allData.setQ_BBBBK(Q_BBBBK);

            float Q_KKK = 0;
            if (Q_KKK != 0) allData.setQ_KKK(Q_KKK);

            float G_BBBBK = 0;
            if (G_BBBBK != 0) allData.setG_BBBBK(G_BBBBK);

            float G_KKK = 0;
            if (G_KKK != 0) allData.setG_KKK(G_KKK);

            float R_BBBBK_05 = 0;
            if (R_BBBBK_05 != 0) allData.setR_BBBBK_05(R_BBBBK_05);

            float R_KKK_05 = 0;
            if (R_KKK_05 != 0) allData.setR_KKK_05(R_KKK_05);

            float D_BBBBK_033 = 0;
            if (D_BBBBK_033 != 0) allData.setD_BBBBK_033(D_BBBBK_033);

            float D_KKK_033 = 0;
            if (D_KKK_033 != 0) allData.setD_KKK_033(0);

            float BBBBK_F_025 = 0;
            if (BBBBK_F_025 != 0) allData.setBBBBK_F_025(BBBBK_F_025);

            float KKK_F_025 = 0;
            if (KKK_F_025 != 0) allData.setKKK_F_025(KKK_F_025);

            float E_GG_2_006_N_BBBBK_25 = 0;
            if (E_GG_2_006_N_BBBBK_25 != 0) allData.setE_GG_2_006_N_BBBBK_25(E_GG_2_006_N_BBBBK_25);

            float E_2_GGK_006_N_KKK_25 = 0;
            if (E_2_GGK_006_N_KKK_25 != 0) allData.setE_2_GGK_006_N_KKK_25(E_2_GGK_006_N_KKK_25);

            float All = 0;
            if (All != 0) allData.setAll(All);

            float All2 = 0;
            if (All2 != 0) allData.setAll2(All2);
        } else if (type.equals("2")) {
            //ЗАОЧНИК
//            allData.setN(0);
//            allData.setLL_P(0);
//            allData.setLL_PK(0);
//            allData.setPP_GGPP(0);
//            allData.setPP_GGKPP(0);
//            allData.setL_GGL(0);
//            allData.setL_GGKL(0);
//            allData.setI_GG_025(0);
//            allData.setI_GG_026(0);
//            allData.setE_BBBBK_033(0);
//            allData.setE_KKK_033(0);
//            allData.setZaliki(0);
//            allData.setGG_Z_2(0);
//            allData.setM_025_BBBBK(0);
//            allData.setM_025_KKK(0);
//            allData.setQ_BBBBK(0);
//            allData.setQ_KKK(0);
//            allData.setG_BBBBK(0);
//            allData.setG_KKK(0);
//            allData.setR_BBBBK_05(0);
//            allData.setR_KKK_05(0);
//            allData.setD_BBBBK_033(0);
//            allData.setD_KKK_033(0);
//            allData.setBBBBK_F_025(0);
//            allData.setKKK_F_025(0);
//            allData.setE_GG_2_006_N_BBBBK_25(0);
//            allData.setE_2_GGK_006_N_KKK_25(0);
//            allData.setAll(0);
//            allData.setAll2(0);
        }
        crudAllData.save(allData);
    }
}
