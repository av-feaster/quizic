package com.nielitav.quizic;

import com.google.gson.annotations.SerializedName;

//{
//        "id": 716,
//        "question": "An abstraction in kubernetes which defines a logical set of pods and a policy to access them.",
//        "description": null,
//        "answers": {
//        "answer_a": "node",
//        "answer_b": "cluster",
//        "answer_c": "service",
//        "answer_d": "set",
//        "answer_e": null,
//        "answer_f": null
//        },
//        "multiple_correct_answers": "false",
//        "correct_answers": {
//        "answer_a_correct": "false",
//        "answer_b_correct": "false",
//        "answer_c_correct": "true",
//        "answer_d_correct": "false",
//        "answer_e_correct": "false",
//        "answer_f_correct": "false"
//        },
//        "correct_answer": "answer_a",
//        "explanation": "A service is a grouping of pods that are running on the cluster. Services are \"cheap\" and you can have many services within the cluster.",
//        "tip": null,
//        "tags": [
//        {
//        "name": "Kubernetes"
//        }
//        ],
//        "category": "Linux",
//        "difficulty": "Easy"
//        }

public class Questions {

    @SerializedName("id")
    String qId;

    String question,description,multiple_correct_answers,category,difficulty,tip,corr;
    public String getqId() {
        return qId;
    }

    public void setqId(String qId) {
        this.qId = qId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMultiple_correct_answers() {
        return multiple_correct_answers;
    }

    public void setMultiple_correct_answers(String multiple_correct_answers) {
        this.multiple_correct_answers = multiple_correct_answers;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getCorr() {
        return corr;
    }

    public void setCorr(String corr) {
        this.corr = corr;
    }



    public class Answers{
        @SerializedName("answer_a")
        String ansA;
        @SerializedName("answer_b")
        String ansB;

        @SerializedName("answer_c")
        String ansC;
        @SerializedName("answer_d")
        String ansD;
        @SerializedName("answer_e")
        String ansE;
        @SerializedName("answer_f")
        String ansF;

        public String getAnsA() {
            return ansA;
        }
        public void setAnsA(String ansA) {
            this.ansA = ansA;
        }

        public String getAnsB() {
            return ansB;
        }

        public void setAnsB(String ansB) {
            this.ansB = ansB;
        }

        public String getAnsC() {
            return ansC;
        }

        public void setAnsC(String ansC) {
            this.ansC = ansC;
        }

        public String getAnsD() {
            return ansD;
        }

        public void setAnsD(String ansD) {
            this.ansD = ansD;
        }

        public String getAnsE() {
            return ansE;
        }

        public void setAnsE(String ansE) {
            this.ansE = ansE;
        }

        public String getAnsF() {
            return ansF;
        }

        public void setAnsF(String ansF) {
            this.ansF = ansF;
        }



    }
    public class CorrectAnswers {
        @SerializedName("answer_a_correct")
        String ansAC;
        @SerializedName("answer_b_correct")
        String ansBC;
        @SerializedName("answer_c_correct")
        String ansCC;
        @SerializedName("answer_d_correct")
        String ansDC;
        @SerializedName("answer_e_correct")
        String ansEC;
        @SerializedName("answer_f_correct")
        String ansFC;

        public String getAnsAC() {
            return ansAC;
        }

        public void setAnsAC(String ansAC) {
            this.ansAC = ansAC;
        }

        public String getAnsBC() {
            return ansBC;
        }

        public void setAnsBC(String ansBC) {
            this.ansBC = ansBC;
        }

        public String getAnsCC() {
            return ansCC;
        }

        public void setAnsCC(String ansCC) {
            this.ansCC = ansCC;
        }

        public String getAnsDC() {
            return ansDC;
        }

        public void setAnsDC(String ansDC) {
            this.ansDC = ansDC;
        }

        public String getAnsEC() {
            return ansEC;
        }

        public void setAnsEC(String ansEC) {
            this.ansEC = ansEC;
        }

        public String getAnsFC() {
            return ansFC;
        }

        public void setAnsFC(String ansFC) {
            this.ansFC = ansFC;
        }
    }

   Answers answers;
    CorrectAnswers correct_answers;

}
