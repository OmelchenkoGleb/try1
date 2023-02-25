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

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
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
        if (allData.getE() == 1) allData.setN(allData.getObsyag()-30) ; else allData.setN(allData.getObsyag());

        float LL_P = allData.getP()*allData.getLL();
        allData.setLL_P(LL_P);

        float LL_PK = allData.getLL()*allData.getPK();
        allData.setLL_PK(LL_PK);

        float PP_GGPP = allData.getPP()*allData.getGGPP();
        allData.setPP_GGPP(PP_GGPP);

        float PP_GGKPP = allData.getPP()*allData.getGGKPP();
        allData.setPP_GGKPP(PP_GGKPP);

        float L_GGL = allData.getL()*allData.getGGL();
        allData.setL_GGL(L_GGL);

        float L_GGKL = allData.getL()*allData.getGGKL();
        allData.setL_GGKL(L_GGKL);

        float I_GG_025 = allData.getI()*allData.getGG()*0.25f;
        allData.setI_GG_025(I_GG_025);

        float I_GG_026 = allData.getI()*allData.getGG()*0.26f;
        allData.setI_GG_026(I_GG_026);

        float E_BBBBK_033 = 0.33f*allData.getE()*(allData.getBB()+allData.getBBk());
        allData.setE_BBBBK_033(E_BBBBK_033);

        float E_KKK_033 = 0.33f*allData.getE()*(allData.getK()+allData.getKK());
        allData.setE_KKK_033(E_KKK_033);

        float Zaliki = allData.getGG()*allData.getZ()*2;
        allData.setZaliki(Zaliki);

        float M_025_BBBBK = 0.25f*allData.getM()*(allData.getBB()+allData.getBBk());
        allData.setM_025_BBBBK(M_025_BBBBK);

        float M_025_KKK = 0.25f*allData.getM()*(allData.getK()+allData.getKK());
        allData.setM_025_KKK(M_025_KKK);

        float Q_BBBBK = allData.getQ()*(allData.getBB()+allData.getBBk());
        allData.setQ_BBBBK(Q_BBBBK);

        float Q_KKK = allData.getQ()*(allData.getK()+allData.getKK());
        allData.setQ_KKK(Q_KKK);

        float G_BBBBK = allData.getG()*(allData.getBB()+allData.getBBk());
        allData.setG_BBBBK(G_BBBBK);

        float G_KKK = allData.getG()*(allData.getK()+allData.getKK());
        allData.setG_KKK(G_KKK);

        float R_BBBBK_05 = 0.5f*allData.getR()*(allData.getBB()+allData.getBBk());
        allData.setR_BBBBK_05(R_BBBBK_05);

        float R_KKK_05 = 0.5f*allData.getR()*(allData.getK()+allData.getKK());
        allData.setR_KKK_05(R_KKK_05);

        float D_BBBBK_033 = 0.33f*allData.getD()*(allData.getBB()+allData.getBBk());
        allData.setD_BBBBK_033(D_BBBBK_033);

        float D_KKK_033 = 0.33f*allData.getD()*(allData.getK()+allData.getKK());
        allData.setD_KKK_033(D_KKK_033);

        float BBBBK_F_025 = 0.25f*allData.getF()*(allData.getBB()+allData.getBBk());
        allData.setBBBBK_F_025(BBBBK_F_025);

        float KKK_F_025 = 0.25f*allData.getF()*(allData.getK()+allData.getKK());
        allData.setKKK_F_025(KKK_F_025);

        float GG_Z_2 = 0;
        float E_GG_2_006_N_BBBBK_25 = 0;
        float E_2_GGK_006_N_KKK_25 = 0;
        if (type.equals("1")) {
            //ОЧНИК
            GG_Z_2 = allData.getZ()*allData.getGGKPP();
            E_GG_2_006_N_BBBBK_25 = 2*allData.getE()*allData.getGG()+0.06f*allData.getN()*(allData.getBB()+allData.getBBk())/25;
            E_2_GGK_006_N_KKK_25 = 2*allData.getE()*allData.getGGk()+0.06f*allData.getN()*(allData.getK()+allData.getKK())/25;
        } else if (type.equals("2")) {
            //ЗАОЧНИК
            GG_Z_2 = allData.getZ()*allData.getGGk();
            E_GG_2_006_N_BBBBK_25 = 2*allData.getE()*allData.getGG()+0.12f*allData.getN()*(allData.getBB()+allData.getBBk())/25;
            E_2_GGK_006_N_KKK_25 = 2*allData.getE()*allData.getGGk()+0.12f*allData.getN()*(allData.getK()+allData.getKK())/25;
        }
        allData.setGG_Z_2(GG_Z_2);
        allData.setE_GG_2_006_N_BBBBK_25(E_GG_2_006_N_BBBBK_25);
        allData.setE_2_GGK_006_N_KKK_25(E_2_GGK_006_N_KKK_25);

        float All = LL_P + PP_GGPP + L_GGL + I_GG_025 + E_BBBBK_033 + Zaliki + M_025_BBBBK + Q_BBBBK + G_BBBBK + R_BBBBK_05 + D_BBBBK_033 + BBBBK_F_025 + E_GG_2_006_N_BBBBK_25;
        allData.setAll(All);
        float All2 = LL_PK + PP_GGKPP + L_GGKL + I_GG_026 + E_KKK_033 + GG_Z_2 + M_025_KKK + Q_KKK + G_KKK + R_KKK_05 + D_KKK_033 + KKK_F_025 + E_2_GGK_006_N_KKK_25;
        allData.setAll2(All2);
        crudAllData.save(allData);
    }
}
