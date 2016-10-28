package com.example.b1014100_2.projectmainver3.quiz;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.b1014100_2.projectmainver3.R;

/**
 * Created by b1014169 on 2016/10/07.
 */
public class QuizData {
    protected static String name;// 魚の名前
    protected static int imageId;// 画像id
    protected static String question;// 問題文
    protected static String[] choices = new String[3];// 選択肢
    protected static String comment;// 解説
    protected static String answer;// 答えのテキスト

    protected static String getAnswer(){
        return "答えは・・・「" + answer + "」だよ";
    }

    // i番目のクイズデータを取得
    protected static void set(int i){
        // 1番　アイナメ
        if(i == 1){
            name = "アイナメ";
            imageId = R.drawable.ainame_1;
            question = "30センチ以上のアイナメはビールビンと呼ばれますが、40センチ以上は？";
            choices[0] = "イッショウビン";
            choices[1] = "ギュウニュウビン";
            choices[2] = "ペットボトル";
            answer = choices[0];
            comment = "アイナメは、関東の釣り師の間では、30センチを超えるとビール瓶、40センチ以上になると一升瓶と呼ばれているよ。";
        }
        // 2番　アカガレイ
        if(i == 2){
            name = "アカガレイ";
            imageId = 0;
            question = "アカガレイの寿命は何年かな？";
            choices[0] = "13年";
            choices[1] = "15年";
            choices[2] = "17年";
            answer = choices[0];
            comment = "アカガレイの寿命は15年！体長は、1歳で10センチ、3歳で20センチ、6歳で30センチと成長していくよ。";
        }
        // 3番　アメマス
        if(i == 3){
            name = "アメマス";
            imageId = 0;
            question = "海で釣れるアメマスを特に何と呼ぶかな？";
            choices[0] = "海マス";
            choices[1] = "海アメ";
            choices[2] = "海アマ";
            answer = choices[1];
            comment = "海で釣れるアメマスは、「海アメ」と呼ばれることもあるよ。";
        }
        // 4番　アユ
        if(i == 4){
            name = "アユ";
            imageId = 0;
            question = "アユは、生まれてすぐに川から海に下っていくんだ。そのときの半透明で細長いアユは、何と呼ばれているかな？";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 5番　イカナゴ
        if(i == 5){
            name = "イカナゴ";
            imageId = 0;
            question = "イカナゴの名前の由来はどれでしょう？";
            choices[0] = "イカの子供だから";
            choices[1] = "何の子供かわからないから";
            choices[2] = "";
            answer = choices[1];
            comment = "解説";
        }
        // 6番　イシガレイ
        if(i == 6){
            name = "イシガレイ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 7番　ウグイ
        if(i == 7){
            name = "ウグイ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 8番　ウバガイ
        if(i == 8){
            name = "ウバガイ";
            imageId = 0;
            question = "ウバガイは、「ウバガイ」の他によく呼ばれる名前があるんだ。それはどれかな？";
            choices[0] = "ホッキガイ";
            choices[1] = "ハッキガイ";
            choices[2] = "フッキガイ";
            answer = choices[0];
            comment = "解説";
        }
        // 9番　エゾアワビ
        if(i == 9){
            name = "エゾアワビ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 10番　エゾバフンウニ
        if(i == 10){
            name = "エゾバフンウニ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 11番　エゾメバル
        if(i == 11){
            name = "エゾメバル";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 12番　オオサガ
        if(i == 12){
            name = "オオサガ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 13番　ガゴメ
        if(i == 13){
            name = "ガゴメ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 14番　カタクチイワシ
        if(i == 14){
            name = "カタクチイワシ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 15番　キタムラサキウニ
        if(i == 15){
            name = "キタムラサキウニ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 16番　キチジ
        if(i == 16){
            name = "キチジ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 17番　クロガシラガレイ
        if(i == 17){
            name = "クロガシラガレイ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 18番　クロマグロ
        if(i == 18){
            name = "クロマグロ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 19番　ケガニ
        if(i == 19){
            name = "ケガニ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 20番　ケムシカジカ
        if(i == 20){
            name = "ケムシカジカ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 21番　コイ
        if(i == 21){
            name = "コイ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 22番　サクラマス/ヤマメ
        if(i == 22){
            name = "サクラマス";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 23番　サラガイ
        if(i == 23){
            name = "サラガイ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 24番　サンマ
        if(i == 24){
            name = "サンマ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 25番　シシャモ
        if(i == 25){
            name = "シシャモ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 26番　シラウオ
        if(i == 26){
            name = "シラウオ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 27番　サケ
        if(i == 27){
            name = "サケ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 28番　スケトウダラ
        if(i == 28){
            name = "スケトウダラ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 29番　スナガレイ
        if(i == 29){
            name = "スナガレイ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 30番　スルメイカ
        if(i == 30){
            name = "スルメイカ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 31番　ソウハチ
        if(i == 31){
            name = "ソウハチ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 32番　チカ
        if(i == 32){
            name = "チカ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 33番　トクビレ
        if(i == 33){
            name = "トクビレ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 34番　トゲカジカ
        if(i == 34){
            name = "トゲカジカ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 35番　ドジョウ
        if(i == 35){
            name = "ドジョウ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 36番　トヤマエビ/ボタンエビ
        if(i == 36){
            name = "トヤマエビ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 37番　ナガヅカ
        if(i == 37){
            name = "ナガヅカ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 38番　ニジマス
        if(i == 38){
            name = "ニジマス";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 39番　バカガイ
        if(i == 39){
            name = "バカガイ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 40番　ハタハタ
        if(i == 40){
            name = "ハタハタ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 41番　ヒメエゾボラ
        if(i == 41){
            name = "ヒメエゾボラ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 42番　ヒラメ
        if(i == 42){
            name = "ヒラメ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 43番　ベニザケ
        if(i == 43){
            name = "ベニザケ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 44番　ホタテガイ
        if(i == 44){
            name = "ホタテガイ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 45番　ホッケ
        if(i == 45){
            name = "ホッケ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 46番　ホッコクアカエビ
        if(i == 46){
            name = "ホッコクアカエビ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 47番　ホテイウオ
        if(i == 47){
            name = "ホテイウオ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 48番　マアナゴ
        if(i == 48){
            name = "マアナゴ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 49番　マイワシ
        if(i == 49){
            name = "マイワシ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 50番　マガキ
        if(i == 50){
            name = "マガキ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 51番　マガレイ
        if(i == 51){
            name = "マガレイ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 52番　マコガレイ
        if(i == 52){
            name = "マコガレイ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 53番　マコンブ
        if(i == 53){
            name = "マコンブ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 54番　マスノスケ
        if(i == 54){
            name = "マスノスケ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 55番　マダラ
        if(i == 55){
            name = "マダラ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 56番　マツカワ
        if(i == 56){
            name = "マツカワ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 57番　マナマコ
        if(i == 57){
            name = "マナマコ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 58番　マボヤ
        if(i == 58){
            name = "マボヤ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 59番　ミズダコ
        if(i == 59){
            name = "ミズダコ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 60番　ミツイシコンブ
        if(i == 60){
            name = "ミツイシコンブ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 61番　メガネカスベ
        if(i == 61){
            name = "メガネカスベ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 62番　ヤナギダコ
        if(i == 62){
            name = "ヤナギダコ";
            imageId = 0;
            question = "問題文";
            choices[0] = "";
            choices[1] = "";
            choices[2] = "";
            answer = choices[0];
            comment = "解説";
        }
        // 63番　ヤリイカ
        if(i == 63){
            name = "ヤリイカ";
            imageId = 0;
            question = "イカやタコの血は何色でしょう？";
            choices[0] = "赤";
            choices[1] = "青";
            choices[2] = "緑";
            answer = choices[1];
            comment = "イカやタコを切っても赤い血は出ないが、血がないわけではなく、薄い空色の血が流れている。";
        }
    }
}
