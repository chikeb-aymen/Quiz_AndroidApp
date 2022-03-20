package com.programming.quiz;

import java.util.ArrayList;
import java.util.List;

public class QuestionsData {

    private static List<Questions> javaQuestions(){

        final List<Questions> questionsLists = new ArrayList<>();

        final Questions question1 = new Questions("Which Set class should be most popular in a multi-threading environment, considering performance constraint?","HashSet","ConcurrentSkipListSet","LinkedHashSet","CopyOnWriteArraySet","ConcurrentSkipListSet","");
        final Questions question2 = new Questions("Que signifie JRE ?","Java Runtime Engine","Java Realtime Execution","Java Runtime Environment","Java Routine Emulator","Java Runtime Environment","");
        final Questions question3 = new Questions("Compiler du Java donne?","un exécutable","rien","un fichier .jve","du byte code","du byte code","");
        final Questions question4 = new Questions("Le Java reprend la syntaxe du","JavaScript","Perl","C++","PHP","C++","");
        final Questions question5 = new Questions("Lorsque la portée d'une classe n'est pas précisée dans un package, celle-ci est dite","public","private","protected","default","default","");
        final Questions question6 = new Questions("Le mot clé \"extends\" est utilisé pour","étendre la portée d'une variable","l'adressage mémoire","l'héritage d'une classe","inclure une librairie","l'héritage d'une classe","");

        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);
        questionsLists.add(question5);
        questionsLists.add(question6);

        return questionsLists;
    }
    private static List<Questions> phpQuestions(){

        final List<Questions> questionsLists = new ArrayList<>();

        final Questions question1 = new Questions("Les fichiers PHP ont l’extension …. ?",".html",".xml",".php",".ph",".php","");
        final Questions question2 = new Questions("Un script PHP devrait commencer par ___ ?","<php","<?php","<?","@php","<?php","");
        final Questions question3 = new Questions("Quelle version de PHP a introduit Try/catch Exception?","PHP 4","PHP 5","PHP 5.3","PHP 7.2","PHP 5","");
        final Questions question4 = new Questions("Nous pouvons utiliser ___ pour commenter une seule ligne?","/?","//","#","/* */","//","");
        final Questions question5 = new Questions("Laquelle parmi les instructions php suivantes va stocker 55 dans la variable nbr?","int $nbr= 55;","int nbr= 55;","$nbr= 55;","55= $nbr;","$nbr= 55;","");
        final Questions question6 = new Questions("Quelle instruction affiche \"bonjour\" ?","echo(\"bonjour\")","echo 'bonjour'","toutes","echo \"bonjour\"","toutes","");

        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);
        questionsLists.add(question5);
        questionsLists.add(question6);

        return questionsLists;
    }
    private static List<Questions> htmlQuestions(){

        final List<Questions> questionsLists = new ArrayList<>();

        final Questions question1 = new Questions("Quelle organisation définit les standards Web?","Apple Inc.","IBM Corporation","World Wide Web Consortium","Microsoft Corporation","World Wide Web Consortium","");
        final Questions question2 = new Questions("HTML est considéré comme ______ ?","Langage de programmation","Langage POO","Langage de haut niveau","Langage de balisage","Langage de balisage","");
        final Questions question3 = new Questions("HTML utilise des ______?","Balises définis par l’utilisateur","Balises prédéfinis","Balises fixes définis par le langage","Balises uniquement pour les liens","Balises fixes définis par le langage","");
        final Questions question4 = new Questions("HTML a été proposé pour la première fois l’année ___.","1980","1990","1995","2000","1990","");
        final Questions question5 = new Questions("Lequel des éléments suivants n’est pas un exemple de navigateur?","Mozilla Firefox","Netscape","Microsoft Bing","Opéra","Netscape","");
            final Questions question6 = new Questions("Qui est l’auteur principal du HTML?","Brendan Eich","Tim Berners-Lee","Développeur web","Google Inc","Tim Berners-Lee","");

        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);
        questionsLists.add(question5);
        questionsLists.add(question6);

        return questionsLists;
    }
    private static List<Questions> androidQuestions(){

        final List<Questions> questionsLists = new ArrayList<>();

        final Questions question1 = new Questions("Sur quelle version de Java Android s’exécute t'il ?","Java Standard Edition","Java Mobile Edition","","","Java Standard Edition","");
        final Questions question2 = new Questions("Où devons nous ajouter une dépendance","Le bloc dependencies du fichier build.gradle (Project)","Le fichier AndroidManifest.xml","Le bloc dependencies du fichier build.gradle (App)","","Le bloc dependencies du fichier build.gradle (App)","");
        final Questions question3 = new Questions("Dans les vues, quelles sont les attributs obligatoires ?","layout_width","layout_height","id","visible","layout_width","");
        final Questions question4 = new Questions("Quelles déclarations sont justes et recommandées ?","TextView android:text=\"TextView\" ....","TextView android:label=\"TextView\" ....","TextView android:text=\"@string/monText\" ..","","TextView android:text=\"TextView\"","");
        final Questions question5 = new Questions("Quelle classe permet d'ouvrir une autre activité ?","Handler","View","Intent","","Intent","");
        final Questions question6 = new Questions("Quelle méthode est appelée lorsque l'activité est affichée ?","onStart()","onResume()","onCreate()","","onCreate()","");

        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);
        questionsLists.add(question5);
        questionsLists.add(question6);

        return questionsLists;
    }

    public static List<Questions> getQuestion(String selectedTopicName){
        switch (selectedTopicName){
            case "java":
                return javaQuestions();
            case "php":
                return phpQuestions();
            case "html":
                return htmlQuestions();
            default:
                return androidQuestions();
        }
    }

}
