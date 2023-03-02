
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Ali Bakri - 12190035 - ICT 311

public class Game {

    JFrame window; //  declaring the JFrame Window
    Container cont; //   declaring cont as a container
    JPanel titleNamePanel, startButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel; // Declaring panel names
    JLabel titleNameLabel, hpLabel, hpLabelNumber, weaponLabel, weaponLabelName, endingLabel; // Declaring the label names
    Font titleFont = new Font("Times New Roman", Font.BOLD, 90); //Declaring font for the title screen
    Font normalFont = new Font("Times New Roman", Font.BOLD, 28); //Declaring font for the rest of the project
    JButton startButton, choice1, choice2, choice3, choice4;
    JTextArea mainTextArea;
    int playerHP, enemyHP, chocolateBar;
    String weapon, position;

    TitleScreenAction tsAction = new TitleScreenAction(); // For Action on Title screen choices
    ChoiceAction choiceAction = new ChoiceAction();      // For Action on in-game choices


    public static void main(String[] args) {

        new Game();
    }
    /////////////////////////////////////// Window Panel /////////////////////////////////////////////////////

    public Game(){
        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//This will terminate the whole app on close that you have to
        window.setLayout(null);                              // compile and run the app again not just rerun the app
        cont = window.getContentPane(); //To display the window Jframe

        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(-10, 100, 800, 150); //".setBounds" decides the position and size of the added components
        titleNameLabel = new JLabel("Legacy of a Fighter"); //Game title
        titleNameLabel.setFont(titleFont);//Using the font for the title

        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300, 400, 200, 100);

        startButton = new JButton("START");
        startButton.setFont(normalFont); // Font for the first button on the title screen
        startButton.addActionListener(tsAction); //giving the start button a role
        startButton.setFocusPainted(false); //removes the focus rectangle out of the button

        titleNamePanel.add(titleNameLabel); // adding the title name
        startButtonPanel.add(startButton);  // adding the start button panel

        cont.add(titleNamePanel);      //setting the title panel to be visible on screen
        cont.add(startButtonPanel);   //setting the start button panel to be visible on screen

        window.setVisible(true);     //setting the whole window frame to appear on screen
    }


    ////////////////////////////////////////// Game Screen /////////////////////////////////////////////////////////////

    public void createGameScreen(){
        titleNamePanel.setVisible(false);   // setting the title panel and start button panel to be invisible on screen
        startButtonPanel.setVisible(false); // because we are now in the in game screen and we dont need them

        mainTextPanel = new JPanel(); //creating the main text panel
        mainTextPanel.setBounds(100, 100, 600, 250);
        cont.add(mainTextPanel);  // displaying the main text panel
        mainTextArea = new JTextArea(" This game is going to be great. Hope you like it !!!!");
        mainTextArea.setBounds(100, 100, 600, 250);
        mainTextArea.setFont(normalFont); //getting the main text area font  from the normal font, which is made for the in-game font
        mainTextArea.setLineWrap(true); // sets boundaries for a text box so words wont shift outside and disappear
        mainTextArea.setWrapStyleWord(true); // eliminates getting words split at the end of the first and the beginning of the
        mainTextArea.setEditable(false); // stops players from editing the text fields                             //second line
        mainTextPanel.add(mainTextArea); // adding the main text area to the panels


        ///////////////////////////////// Button Panel and Buttons /////////////////////////////////////////////

        choiceButtonPanel = new JPanel(); //creating a new panel for buttons
        choiceButtonPanel.setBounds(250, 350, 300, 150); //panel size
        choiceButtonPanel.setLayout(new GridLayout(4,1)); // dividing the panel into rows and columns
        cont.add(choiceButtonPanel); // adding the choice panel

        choice1 = new JButton("Choice 1"); // creating button 1
        choice1.setFont(normalFont);
        choice1.addActionListener(choiceAction); //setting a role for this button, to execute on choice action
        choice1.setActionCommand("c1"); // passing c1 as the name of the action choice from the action choice menu below
        choiceButtonPanel.add(choice1); //adding the button to the button panel
        choice1.setFocusPainted(false);

        // Same documentation applies for the rest of the 3 buttons but with different names

        choice2 = new JButton("Choice 2");
        choice2.setFont(normalFont);
        choice2.addActionListener(choiceAction);
        choice2.setActionCommand("c2");
        choiceButtonPanel.add(choice2);
        choice2.setFocusPainted(false);

        // Same documentation applies for the rest of the 2 buttons but with different names
        choice3 = new JButton("Choice 3");
        choice3.setFont(normalFont);
        choice3.addActionListener(choiceAction);
        choice3.setActionCommand("c3");
        choiceButtonPanel.add(choice3);
        choice3.setFocusPainted(false);

        // Same documentation applies for remaining button but with different names
        choice4 = new JButton("Choice 4");
        choice4.setFont(normalFont);
        choice4.addActionListener(choiceAction);
        choice4.setActionCommand("c4");
        choiceButtonPanel.add(choice4);
        choice4.setFocusPainted(false);


        ///////////////////////////////////////// PLAYER PANEL /////////////////////////////////////////////////////////

        playerPanel = new JPanel(); //creating a panel called player panel
        playerPanel.setBounds(100, 15, 600, 50);
        playerPanel.setLayout(new GridLayout(1,4));
        cont.add(playerPanel); // adding this panel to the container where the rest of the panels are
        hpLabel = new JLabel("HP:"); // The player health point labeling at the top left of the screen
        hpLabel.setFont(normalFont);
        playerPanel.add(hpLabel); // adding the hp to the player panel
        hpLabelNumber = new JLabel();
        hpLabelNumber.setFont(normalFont);
        playerPanel.add(hpLabelNumber); // adding the hp label number to the player panel
        weaponLabel = new JLabel("Weapon:"); //declaring new label for the weapon of the player
        weaponLabel.setFont(normalFont);
        playerPanel.add(weaponLabel); // adding the weapon label to the player panel
        weaponLabelName = new JLabel(); // declaring a new label for the weapon name
        weaponLabelName.setFont(normalFont);
        playerPanel.add(weaponLabelName); // adding weapon label name to the panel

        playerSetup();// passing the values from the player setup to the player in player panel

    }
    public void playerSetup(){

        playerHP = 15; // passing the player hp
        enemyHP = 20; // passing the enemy hp
        weapon = "Knife"; // passing the weapon used by the player
        weaponLabelName.setText(weapon); //passing the weapon knife as default because it may change later on
        hpLabelNumber.setText("" + playerHP); //passing the player hp cuz it subjected to change

        townGate(); // passing town gate so that the player starts there with different options
    }

    public void townGate(){
        position = "townGate"; // creating a player position called townGate
        mainTextArea.setText("You are at the gate of the town. \nA guard is standing in front of you. \n\nWhat do you do?"); //What to display on town gate
        choice1.setText("Talk to the guard"); //Option 1 for the player when at the town gate
        choice2.setText("Attack the guard"); //Option 2 for the player when at the town gate
        choice3.setText("Leave");           //Option 3 for the player when at the town gate
        choice4.setText("");               //Option 4 for the player when at the town gate
    }
    public void talkGuard(){
        position = "talkGuard"; // creating a new player position called talk guard, this when the player reaches the guard
        mainTextArea.setText("Guard: Hello stranger. I have never seen your face. \nI'm sorry but we cannot let a stranger enter our town.");
        choice1.setText("Back"); // return back, there will be no approach towards the town at this early level
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }
    public void attackGuard(){
        position = "attackGuard";
        mainTextArea.setText("Guard: You Shall not pass!\n\nThe guard fought back and hit you hard.\n(You receive 3 damage)");

        playerHP -=3; // decrementing player hp by 3 due to guard attack
        hpLabelNumber.setText(""+playerHP); // displaying new player hp after the damage received by every guard attack
        choice1.setText("Back"); // return back, to make other wise choices
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }
    public void crossRoad(){
        position = "crossRoad"; //player position at a cross road between 4 ways
        mainTextArea.setText("You are at a crossroad.\nIf you go south, you will go back to the town."); //displaying text so player knows that town is south
        choice1.setText("Go north");   //option for player to go north
        choice2.setText("Go east");   //option for player to go east
        choice3.setText("Go south"); //option for player to go south
        choice4.setText("Go west"); //option for player to go west
    }
    public void north(){
        position = "north"; //heading north for a new discovery
        mainTextArea.setText("There is a river. \nYou drink the water and rest at the riverside. \n\n(Your HP is recovered by 2)");
        playerHP = playerHP + 2; // incrementing the value of the player hp by 2 after drinking water
        hpLabelNumber.setText(""+playerHP); // changing and passing the player hp in the panel
        choice1.setText("Go south"); // going south so that your reach the crossroad again
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }
    public void east(){
        position = "east"; //heading east
        mainTextArea.setText("You walked into a forest and found a Long Sword!\n\n(You obtained a Long Sword)");//displaying the following text so player knows
        weapon = "Long Sword"; // weapon name                                                                             //that he found a sword in the forest
        weaponLabelName.setText(weapon); //passing new weapon name to display
        choice1.setText("Go west"); // going west to reach cross road again
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");

    }
    public void west(){
        position = "west"; // west position
        mainTextArea.setText("You encounter an enemy!"); //passing a text that displays here to alert that player encounters and enemy
        choice1.setText("Fight"); //option 1 so that the player may fight if ready
        choice2.setText("Run");  //option 2 so that the player runs away incase not ready at it will take him back to the crossroad
        choice3.setText("");
        choice4.setText("");
    }
    public void fight(){
        position = "fight"; //position where the player engages an enemy
        mainTextArea.setText("Enemy HP: " + enemyHP + "\n\nWhat do you do?"); // passing the enemy stats for the main text area
        choice1.setText("Attack"); // checking if the player wants to fight or run again before attacking the enemy
        choice2.setText("Run");                                                 //and after displaying the enemy hp
        choice3.setText("");
        choice4.setText("");
    }
    public void playerAttack(){
        position = "playerAttack"; //position where player attacks the enemy

        int playerDamage = 0; // declaring default player damage = 0

        if(weapon.equals("Knife")){
            playerDamage = new java.util.Random().nextInt(3);
        } // condition where if the player has only a knife in hand the damage will vary between 0 and 3
        else if(weapon.equals("Long Sword")){
            playerDamage = new java.util.Random().nextInt(12);
        } // condition where if the player has a long sword the damage will vary between 0 and 12 giving a very high chance of dealing a critical damage

        mainTextArea.setText("You attacked the enemy and gave " + playerDamage + " damage!"); // passing the main text area a text to display for the players
                                                                                                  // that states the amount of damage dealt to the enemy
        enemyHP = enemyHP - playerDamage; // decrementing the enemy hp by the random amount of damage generated above

        choice1.setText("Back"); //going back will display the amount of damage dealt to the player by the enemy
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }
    public void enemyAttack(){
        position = "enemyAttack"; // here the enemy will attack back the player after the player attack is done

        int enemyDamage = 0; // declaring the enemy damage value as 0

        enemyDamage = new java.util.Random().nextInt(6); // generating a random amount of damage between 0 and 6

        mainTextArea.setText("The enemy attacked you and gave " + enemyDamage + " damage!"); // displaying the amount of damage dealt to the player

        playerHP = playerHP - enemyDamage; // subtracting the initial player hp from the random amount generated and dealt by the enemy
        hpLabelNumber.setText(""+playerHP); //displaying the new player hp after the change

        choice1.setText("Back"); // heading back where the option is fight or run
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }
    public void win(){
        position = "win"; // position if the player wins and game ends

        mainTextArea.setText("You defeated the enemy!\nThe enemy dropped a chocolate bar!\n\n( You obtained a chocolate bar )"); // text on win

        chocolateBar = 1; // value of the chocolate bar

        choice1.setText("Go east"); // assign the first choice to head back east which returns the player back to the crossroad
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");

    }
    public void lose(){
        position = "lose"; // position where the player loses and game ends

        mainTextArea.setText("You are dead!\n\nTake the L you big fat loser");

        choice1.setText("Loser");   // setting the choice button to show the word loser without any action to be taken
        choice2.setText("Loser");  // setting the choice button to show the word loser without any action to be taken
        choice3.setText("Loser"); // setting the choice button to show the word loser without any action to be taken
        choice4.setText("Loser");// setting the choice button to show the word loser without any action to be taken

    }
    public void ending(){
        position = "ending"; // This position is after you've beaten the game

        mainTextArea.setText("Guard: Oh you killed that enemy!?\nYou are true hero!\nWelcome to our town!\n\nTHE END"); //text display



        choice1.setText("");             //empty button value
        choice2.setText("");            //empty button value
        choice3.setText("");           //empty button value
        choice4.setText("");          //empty button value
        choice1.setVisible(false);   // disabling the choices so that
        choice2.setVisible(false);  // the player cannot return back
        choice3.setVisible(false); // after the game is over
        choice4.setVisible(false);
    }




    public class TitleScreenAction implements ActionListener{ // Setting an action lister so that the button

        public void actionPerformed(ActionEvent event){     //  perform action when clicked and by that

            createGameScreen();                           // it is going to create the game screen
        }
    }


    public class ChoiceAction implements ActionListener{     // Setting an action lister so that the choice

        public void actionPerformed(ActionEvent event){     // buttons perform an action when clicked on

            String yourChoice = event.getActionCommand(); // declaring a string and event.getActionCommand gives you a
                                                         //  String representing event of the action command

            switch(position){  //switch menu of name position to declare the player position when chosen
                case "townGate":
                    switch(yourChoice){ // nested switch menu to identify the choice of player after taking the above choice

                        case "c1":              // casing running condition, if the chocolate bar is
                            if(chocolateBar == 1){ // found guard will appreciate player and then it
                                ending();     // will run the ending parameter and end game
                            }
                            else{
                                talkGuard(); // else it  runs talk guard parameter and guard will talk to player and wont let him in
                            }
                            break;
                        case "c2": attackGuard();break; // runs attack guard parameters where guard takes action over player damaging him
                        case "c3":
                            crossRoad();break; // case running a button labeled leave that redirects the player to the 4 crossroads of the game
                    }
                    break; // break ending the mother case townGate
                case "talkGuard": //case related to talkguard scenario
                    switch(yourChoice){
                        case "c1": townGate(); break; // choice that sets the player  at town gate with 3 options
                    }
                    break; // break ending the case talkGuard
                case "attackGuard": // case of attack guard
                    switch(yourChoice){
                        case "c1": townGate(); break; //case related to button called back that redirects player to town gate to take actions again
                    }
                    break; // break ending the case attackGuard
                case "crossRoad": // case relate to button labled leave that takes the player to the cross roads
                    switch(yourChoice){
                        case "c1": north(); break; // related to a button labeled north that runs the parameter north and an event takes place
                        case "c2": east();break; // related to a button labeled east that runs the parameter east and an event takes place
                        case "c3": townGate(); break; // related to a button labeled south that runs the parameter towngate and takes player to town gate where he started
                        case "c4": west();break; // related to a button labeled west that runs the parameter west and an event takes place
                    }
                    break; // break ending the case crossRoad
                case "north":
                    switch(yourChoice){
                        case "c1": crossRoad(); break; // button labeled go south appears after and even takes place in the game, this case redirects players to the 4 cross roads
                    }
                    break; // break ending the case north
                case "east":
                    switch(yourChoice){
                        case "c1": crossRoad(); break; // button labeled go west appears after and even takes place in the game, this case redirects players to the 4 cross roads
                    }
                    break; // break ending the case east
                case "west":
                    switch(yourChoice){
                        case "c1": fight(); break; //button labeled fight that will run the parameter fight and player fights enemy
                        case "c2": crossRoad(); break; //button labeled run that will redirect player to the crossroads
                    }
                    break; // break ending the case west
                case "fight": //
                    switch(yourChoice){
                        case "c1": playerAttack();break; // button labeled attack, runs parameter player attack then events take place in the game
                        case "c2": crossRoad(); break;  // button labeled run holding function crossroads that redirects player to the crossroads
                    }
                    break; // break ending the case fight
                case "playerAttack": //taking place after the above choice c1 of playerattack parameter in fight has been selected
                    switch(yourChoice){
                        case "c1": //button holding the label back that if the below condition is applied the player wins and a display is available
                            if(enemyHP <1 ){
                                win();
                            }
                            else{
                                enemyAttack(); //otherwise the enemy attacks back causing damage to player and the game continues
                            }
                            break;
                    }
                    break; // break ending the case PlayerAttack
                case "enemyAttack": // case taking place after the above condition isnt satisfied
                    switch(yourChoice){
                        case "c1":
                            if(playerHP <1 ){ // it checks if the player hp is below 1 it runs the
                                lose();      // parameter lose, player loses and it humiliates him
                            }
                            else{           // if not the player continues to fight until
                                fight();   //  either the enemy loses or the player
                            }
                            break;
                    }
                    break; // break ending the case enemyAttack
                case "win": //button labeled back --->
                    switch(yourChoice){
                        case "c1": crossRoad(); //that redirects the player using function crossroad to the crossroads of the gmae
                    }
                    break;

            }


        }
    }

}
