package diplom.try1.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "report")
public class report {
    @jakarta.persistence.Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    @Column(name = "name")
    private String name;
    @Column(name = "N")
    private float N;

    @Column(name = "Л х Р")
    private float LL_P;
    @Column(name = "Л х РК")
    private float LL_PK;
    @Column(name = "П х ГП")
    private float PP_GGPP;
    @Column(name = "П х ГКП")
    private float PP_GGKPP;
    @Column(name = "L х ГL")
    private float L_GGL;
    @Column(name = "L х ГКL")
    private float L_GGKL;
    @Column(name = "ІхГх0,25")
    private float I_GG_025;
    @Column(name = "ІхГх0,26")
    private float I_GG_026;


    @Column(name = "0.33хEx(Б+БК)")
    private float E_BBBBK_033;
    @Column(name = "0.33хEx(К+КК)")
    private float E_KKK_033;
    @Column(name = "Заліки,+AL10")
    private float zaliki;



    @Column(name = "2хГхZ")
    private float GG_Z_2;
    @Column(name = "0.25хМх(Б+БК)")
    private float M_025_BBBBK;
    @Column(name = "0.25хМх(К+КК)")
    private float M_025_KKK;
    @Column(name = "Qх(Б+БК)")
    private float Q_BBBBK;
    @Column(name = "Qх(К+КК)")
    private float Q_KKK;
    @Column(name = "Gх(Б+БК)")
    private float G_BBBBK;
    @Column(name = "Gх(К+КК)")
    private float G_KKK;
    @Column(name = "0.5хRх(Б+БК)")
    private float R_BBBBK_05;
    @Column(name = "0.5хRх(К+КК)")
    private float R_KKK_05;
    @Column(name = "0.33хDх(Б+БК)")
    private float D_BBBBK_033;
    @Column(name = "0.33хDх(К+КК)")
    private float D_KKK_033;
    @Column(name = "0.25хFх(Б+БК)")
    private float BBBBK_F_025;
    @Column(name = "0.25хFх(К+КК)")
    private float KKK_F_025;
    @Column(name = "2 х E х Г + 0,06 х N х (Б+БК)/25")
    private float E_GG_2_006_N_BBBBK_25;
    @Column(name = "2хЕхГК+0,06хNх(К+КК)/25")
    private float E_2_GGK_006_N_KKK_25;
    @Column(name = "Всього годин")
    private float all;
    @Column(name = "Всього годин 2")
    private float all2;
}
