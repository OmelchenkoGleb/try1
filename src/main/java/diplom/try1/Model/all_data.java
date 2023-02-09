package diplom.try1.Model;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;


@Entity
@Getter
@Setter
@Table(name = "all_data")
public class all_data {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "semestr")
    private double semestr;
    @Column(name = "Назва дисципліни, курс, шифр груп")
    private String name;
    @Column(name = "N")
    private double N;
    @Column(name = "Л")
    private double LL;
    @Column(name = "П")
    private double PP;
    @Column(name = "L")
    private double L;
    @Column(name = "І")
    private double I;
    @Column(name = "Е")
    private double E;
    @Column(name = "Z")
    private double Z;
    @Column(name = "М")
    private double M;
    @Column(name = "Q")
    private double Q;
    @Column(name = "G")
    private double G;
    @Column(name = "R")
    private double R;
    @Column(name = "D")
    private double D;
    @Column(name = "F")
    private double F;
    @Column(name = "Г")
    private double GG;
    @Column(name = "ГП")
    private double GGPP;
    @Column(name = "ГL")
    private double GGL;
    @Column(name = "ГК")
    private double GGk;
    @Column(name = "ГКП")
    private double GGKPP;
    @Column(name = "ГКL")
    private double GGKL;
    @Column(name = "Б")
    private double BB;
    @Column(name = "К")
    private double K;
    @Column(name = "БК")
    private double BBk;
    @Column(name = "КК")
    private double KK;
    @Column(name = "Р")
    private double P;
    @Column(name = "РК")
    private double PK;
    @Column(name = "Л х Р")
    private double LL_P;
    @Column(name = "Л х РК")
    private double LL_PK;
    @Column(name = "П х ГП")
    private double PP_GGPP;
    @Column(name = "П х ГКП")
    private double PP_GGKPP;
    @Column(name = "L х ГL")
    private double L_GGL;
    @Column(name = "L х ГКL")
    private double L_GGKL;
    @Column(name = "ІхГх0,25")
    private double I_GG_025;
    @Column(name = "ІхГх0,26")
    private double I_GG_026;


    @Column(name = "0.33хEx(Б+БК)")
    private double E_BBBBK_033;
    @Column(name = "0.33хEx(К+КК)")
    private double E_KKK_033;
    @Column(name = "Заліки,+AL10")
    private double zaliki;



    @Column(name = "2хГхZ")
    private double GG_Z_2;
    @Column(name = "0.25хМх(Б+БК)")
    private double M_025_BBBBK;
    @Column(name = "0.25хМх(К+КК)")
    private double M_025_KKK;
    @Column(name = "Qх(Б+БК)")
    private double Q_BBBBK;
    @Column(name = "Qх(К+КК)")
    private double Q_KKK;
    @Column(name = "Gх(Б+БК)")
    private double G_BBBBK;
    @Column(name = "Gх(К+КК)")
    private double G_KKK;
    @Column(name = "0.5хRх(Б+БК)")
    private double R_BBBBK_05;
    @Column(name = "0.5хRх(К+КК)")
    private double R_KKK_05;
    @Column(name = "0.33хDх(Б+БК)")
    private double D_BBBBK_033;
    @Column(name = "0.33хDх(К+КК)")
    private double D_KKK_033;
    @Column(name = "0.25хFх(Б+БК)")
    private double BBBBK_F_025;
    @Column(name = "0.25хFх(К+КК)")
    private double KKK_F_025;
    @Column(name = "2 х E х Г + 0,06 х N х (Б+БК)/25")
    private double E_GG_2_006_N_BBBBK_25;
    @Column(name = "2хЕхГК+0,06хNх(К+КК)/25")
    private double E_2_GGK_006_N_KKK_25;
    @Column(name = "Всього годин")
    private double all;
    @Column(name = "Всього годин 2")
    private double all2;
    @Column(name = "Обсяг дисциплін за семестр")
    private double obsyag;



    @Override
    public String toString() {
        return "all_data{" +
                "id=" + id +
                ", semestr=" + semestr +
                ", name='" + name + '\'' +
                ", N=" + N +
                ", LL=" + LL +
                ", PP=" + PP +
                ", L=" + L +
                ", I=" + I +
                ", E=" + E +
                ", Z=" + Z +
                ", M=" + M +
                ", Q=" + Q +
                ", G=" + G +
                ", R=" + R +
                ", D=" + D +
                ", F=" + F +
                ", GG=" + GG +
                ", GGPP=" + GGPP +
                ", GGL=" + GGL +
                ", GGk=" + GGk +
                ", GGKPP=" + GGKPP +
                ", GGKL=" + GGKL +
                ", BB=" + BB +
                ", K=" + K +
                ", BBk=" + BBk +
                ", KK=" + KK +
                ", P=" + P +
                ", PK=" + PK +
                ", LL_P=" + LL_P +
                ", LL_PK=" + LL_PK +
                ", PP_GGPP=" + PP_GGPP +
                ", PP_GGKPP=" + PP_GGKPP +
                ", L_GGL=" + L_GGL +
                ", L_GGKL=" + L_GGKL +
                ", I_GG_025=" + I_GG_025 +
                ", I_GG_026=" + I_GG_026 +
                ", E_BBBBK_033=" + E_BBBBK_033 +
                ", E_KKK_033=" + E_KKK_033 +
                ", zaliki=" + zaliki +
                ", GG_Z_2=" + GG_Z_2 +
                ", M_025_BBBBK=" + M_025_BBBBK +
                ", M_025_KKK=" + M_025_KKK +
                ", Q_BBBBK=" + Q_BBBBK +
                ", Q_KKK=" + Q_KKK +
                ", G_BBBBK=" + G_BBBBK +
                ", G_KKK=" + G_KKK +
                ", R_BBBBK_05=" + R_BBBBK_05 +
                ", R_KKK_05=" + R_KKK_05 +
                ", D_BBBBK_033=" + D_BBBBK_033 +
                ", D_KKK_033=" + D_KKK_033 +
                ", BBBBK_F_025=" + BBBBK_F_025 +
                ", KKK_F_025=" + KKK_F_025 +
                ", E_GG_2_006_N_BBBBK_25=" + E_GG_2_006_N_BBBBK_25 +
                ", E_2_GGK_006_N_KKK_25=" + E_2_GGK_006_N_KKK_25 +
                ", all=" + all +
                ", all2=" + all2 +
                ", obsyag=" + obsyag +
                ", teachers=" + teachers +
                '}';
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teachers_id")
    private Teachers teachers;


}
