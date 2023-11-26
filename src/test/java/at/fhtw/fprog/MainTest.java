package at.fhtw.fprog;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static at.fhtw.fprog.Main.calculateDensity;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {
    static List<String> warTerms;
    static List<String> peaceTerms;

    @BeforeAll
    static void initialize() {
        warTerms = List.of(
                """
                                                afraid
                                                anguish
                                                armed
                                                barbwire
                                                battle
                                                capture
                                                commander
                                                conflict
                                                confrontation
                                                crusade
                                                crying
                                                dead
                                                death
                                                depression
                                                desolation
                                                despair
                                                destitution
                                                destruction
                                                disagreement
                                                enemies
                                                enemy
                                                eradicate
                                                explosion
                                                famine
                                                fear
                                                feud
                                                fight
                                                general
                                                gloom
                                                grenade
                                                grief
                                                gun
                                                gunpowder
                                                hate
                                                hopelessness
                                                hostility
                                                kill
                                                loss
                                                martial
                                                misery
                                                mourn
                                                pain
                                                plane
                                                politics
                                                quarantine
                                                rivalry
                                                sad
                                                shoot
                                                smoke
                                                soldier
                                                struggle
                                                suffer
                                                sword
                                                tank
                                                trench
                                                uniform
                                                wailing
                                                war
                                                weapon
                        """
                        .toLowerCase()
                        .replaceAll("\\p{Punct}+", "")
                        .split("\\s+")
        );
        peaceTerms = List.of(
                """
                                                abundance
                                                agreement
                                                amity
                                                books
                                                breakfast
                                                butter
                                                calm
                                                ceasefire
                                                concord
                                                concurrence
                                                contract
                                                courteous
                                                diplomacy
                                                diplomat
                                                employment
                                                fields
                                                flour
                                                flower
                                                food
                                                freedom
                                                fruit
                                                grain
                                                harmony
                                                harvest
                                                home
                                                house
                                                liberty
                                                love
                                                music
                                                negotiation
                                                party
                                                peace
                                                plenty
                                                profusion
                                                quiet
                                                reconciliation
                                                respite
                                                serenity
                                                smile
                                                stillness
                                                supper
                                                sweet
                                                tranquility
                                                treaty
                                                truce
                                                understanding
                                                union
                                                vegetable
                                                wealth
                                                wonderful
                                                                                                
                        """
                        .toLowerCase()
                        .replaceAll("\\p{Punct}+", "")
                        .split("\\s+")
        );
    }

    @Test
    void calculateDensityTest_OnlyOneWarTerm() {
        List<String> wordList = List.of("afraid", "test", "test", "abundance", "abundance");
        double peaceDensity = calculateDensity(wordList, peaceTerms);
        double warDensity = calculateDensity(wordList, warTerms);
        Chapter chapter = new Chapter(1, peaceDensity, warDensity);
        assertEquals("Chapter 1: war-related", chapter.toString());
    }

    @Test
    void calculateDensityTest_OnlyOnePeaceTerm() {
        List<String> wordList = List.of("abundance", "test", "test", "afraid", "afraid");
        double peaceDensity = calculateDensity(wordList, peaceTerms);
        double warDensity = calculateDensity(wordList, warTerms);
        Chapter chapter = new Chapter(1, peaceDensity, warDensity);
        assertEquals("Chapter 1: peace-related", chapter.toString());
    }

    @Test
    void calculateDensityTest_Chapter1() {
        List<String> wordList = List.of(
                """
                        "Well, Prince, so Genoa and Lucca are now just family estates of the
                        Buonapartes. But I warn you, if you don't tell me that this means war,
                        if you still try to defend the infamies and horrors perpetrated by
                        that Antichrist--I really believe he is Antichrist--I will have
                        nothing more to do with you and you are no longer my friend, no longer
                        my 'faithful slave,' as you call yourself! But how do you do? I see
                        I have frightened you--sit down and tell me all the news."
                                        
                        It was in July, 1805, and the speaker was the well-known Anna
                        Pavlovna Scherer, maid of honor and favorite of the Empress Marya
                        Fedorovna. With these words she greeted Prince Vasili Kuragin, a man
                        of high rank and importance, who was the first to arrive at her
                        reception. Anna Pavlovna had had a cough for some days. She was, as
                        she said, suffering from la grippe; grippe being then a new word in
                        St. Petersburg, used only by the elite.
                                        
                        All her invitations without exception, written in French, and
                        delivered by a scarlet-liveried footman that morning, ran as follows:
                                        
                        "If you have nothing better to do, Count [or Prince], and if the
                        prospect of spending an evening with a poor invalid is not too
                        terrible, I shall be very charmed to see you tonight between 7 and 10-
                        Annette Scherer."
                                        
                        "Heavens! what a virulent attack!" replied the prince, not in the
                        least disconcerted by this reception. He had just entered, wearing
                        an embroidered court uniform, knee breeches, and shoes, and had
                        stars on his breast and a serene expression on his flat face. He spoke
                        in that refined French in which our grandfathers not only spoke but
                        thought, and with the gentle, patronizing intonation natural to a
                        man of importance who had grown old in society and at court. He went
                        up to Anna Pavlovna, kissed her hand, presenting to her his bald,
                        scented, and shining head, and complacently seated himself on the
                        sofa.
                                        
                        "First of all, dear friend, tell me how you are. Set your friend's
                        mind at rest," said he without altering his tone, beneath the
                        politeness and affected sympathy of which indifference and even
                        irony could be discerned.
                                        
                        "Can one be well while suffering morally? Can one be calm in times
                        like these if one has any feeling?" said Anna Pavlovna. "You are
                        staying the whole evening, I hope?"
                                        
                        "And the fete at the English ambassador's? Today is Wednesday. I
                        must put in an appearance there," said the prince. "My daughter is
                        coming for me to take me there."
                                        
                        "I thought today's fete had been canceled. I confess all these
                        festivities and fireworks are becoming wearisome."
                                        
                        "If they had known that you wished it, the entertainment would
                        have been put off," said the prince, who, like a wound-up clock, by
                        force of habit said things he did not even wish to be believed.
                                        
                        "Don't tease! Well, and what has been decided about Novosiltsev's
                        dispatch? You know everything."
                                        
                        "What can one say about it?" replied the prince in a cold,
                        listless tone. "What has been decided? They have decided that
                        Buonaparte has burnt his boats, and I believe that we are ready to
                        burn ours."
                                        
                        Prince Vasili always spoke languidly, like an actor repeating a
                        stale part. Anna Pavlovna Scherer on the contrary, despite her forty
                        years, overflowed with animation and impulsiveness. To be an
                        enthusiast had become her social vocation and, sometimes even when she
                        did not feel like it, she became enthusiastic in order not to
                        disappoint the expectations of those who knew her. The subdued smile
                        which, though it did not suit her faded features, always played
                        round her lips expressed, as in a spoiled child, a continual
                        consciousness of her charming defect, which she neither wished, nor
                        could, nor considered it necessary, to correct.
                                        
                        In the midst of a conversation on political matters Anna Pavlovna
                        burst out:
                                        
                        "Oh, don't speak to me of Austria. Perhaps I don't understand
                        things, but Austria never has wished, and does not wish, for war.
                        She is betraying us! Russia alone must save Europe. Our gracious
                        sovereign recognizes his high vocation and will be true to it. That is
                        the one thing I have faith in! Our good and wonderful sovereign has to
                        perform the noblest role on earth, and he is so virtuous and noble
                        that God will not forsake him. He will fulfill his vocation and
                        crush the hydra of revolution, which has become more terrible than
                        ever in the person of this murderer and villain! We alone must
                        avenge the blood of the just one.... Whom, I ask you, can we rely
                        on?... England with her commercial spirit will not and cannot
                        understand the Emperor Alexander's loftiness of soul. She has
                        refused to evacuate Malta. She wanted to find, and still seeks, some
                        secret motive in our actions. What answer did Novosiltsev get? None.
                        The English have not understood and cannot understand the
                        self-abnegation of our Emperor who wants nothing for himself, but only
                        desires the good of mankind. And what have they promised? Nothing! And
                        what little they have promised they will not perform! Prussia has
                        always declared that Buonaparte is invincible, and that all Europe
                        is powerless before him.... And I don't believe a word that Hardenburg
                        says, or Haugwitz either. This famous Prussian neutrality is just a
                        trap. I have faith only in God and the lofty destiny of our adored
                        monarch. He will save Europe!"
                                        
                        She suddenly paused, smiling at her own impetuosity.
                                        
                        "I think," said the prince with a smile, "that if you had been
                        sent instead of our dear Wintzingerode you would have captured the
                        King of Prussia's consent by assault. You are so eloquent. Will you
                        give me a cup of tea?"
                                        
                        "In a moment. A propos," she added, becoming calm again, "I am
                        expecting two very interesting men tonight, le Vicomte de Mortemart,
                        who is connected with the Montmorencys through the Rohans, one of
                        the best French families. He is one of the genuine emigres, the good
                        ones. And also the Abbe Morio. Do you know that profound thinker? He
                        has been received by the Emperor. Had you heard?"
                                        
                        "I shall be delighted to meet them," said the prince. "But tell me,"
                        he added with studied carelessness as if it had only just occurred
                        to him, though the question he was about to ask was the chief motive
                        of his visit, "is it true that the Dowager Empress wants Baron Funke
                        to be appointed first secretary at Vienna? The baron by all accounts
                        is a poor creature."
                                        
                        Prince Vasili wished to obtain this post for his son, but others
                        were trying through the Dowager Empress Marya Fedorovna to secure it
                        for the baron.
                                        
                        Anna Pavlovna almost closed her eyes to indicate that neither she
                        nor anyone else had a right to criticize what the Empress desired or
                        was pleased with.
                                        
                        "Baron Funke has been recommended to the Dowager Empress by her
                        sister," was all she said, in a dry and mournful tone.
                                        
                        As she named the Empress, Anna Pavlovna's face suddenly assumed an
                        expression of profound and sincere devotion and respect mingled with
                        sadness, and this occurred every time she mentioned her illustrious
                        patroness. She added that Her Majesty had deigned to show Baron
                        Funke beaucoup d'estime, and again her face clouded over with sadness.
                                        
                        The prince was silent and looked indifferent. But, with the
                        womanly and courtierlike quickness and tact habitual to her, Anna
                        Pavlovna wished both to rebuke him (for daring to speak he had done of
                        a man recommended to the Empress) and at the same time to console him,
                        so she said:
                                        
                        "Now about your family. Do you know that since your daughter came
                        out everyone has been enraptured by her? They say she is amazingly
                        beautiful."
                                        
                        The prince bowed to signify his respect and gratitude.
                                        
                        "I often think," she continued after a short pause, drawing nearer
                        to the prince and smiling amiably at him as if to show that
                        political and social topics were ended and the time had come for
                        intimate conversation--"I often think how unfairly sometimes the
                        joys of life are distributed. Why has fate given you two such splendid
                        children? I don't speak of Anatole, your youngest. I don't like
                        him," she added in a tone admitting of no rejoinder and raising her
                        eyebrows. "Two such charming children. And really you appreciate
                        them less than anyone, and so you don't deserve to have them."
                                        
                        And she smiled her ecstatic smile.
                                        
                        "I can't help it," said the prince. "Lavater would have said I
                        lack the bump of paternity."
                                        
                        "Don't joke; I mean to have a serious talk with you. Do you know I
                        am dissatisfied with your younger son? Between ourselves" (and her
                        face assumed its melancholy expression), "he was mentioned at Her
                        Majesty's and you were pitied...."
                                        
                        The prince answered nothing, but she looked at him significantly,
                        awaiting a reply. He frowned.
                                        
                        "What would you have me do?" he said at last. "You know I did all
                        a father could for their education, and they have both turned out
                        fools. Hippolyte is at least a quiet fool, but Anatole is an active
                        one. That is the only difference between them." He said this smiling
                        in a way more natural and animated than usual, so that the wrinkles
                        round his mouth very clearly revealed something unexpectedly coarse
                        and unpleasant.
                                        
                        "And why are children born to such men as you? If you were not a
                        father there would be nothing I could reproach you with," said Anna
                        Pavlovna, looking up pensively.
                                        
                        "I am your faithful slave and to you alone I can confess that my
                        children are the bane of my life. It is the cross I have to bear. That
                        is how I explain it to myself. It can't be helped!"
                                        
                        He said no more, but expressed his resignation to cruel fate by a
                        gesture. Anna Pavlovna meditated.
                                        
                        "Have you never thought of marrying your prodigal son Anatole?"
                        she asked. "They say old maids have a mania for matchmaking, and
                        though I don't feel that weakness in myself as yet, I know a little
                        person who is very unhappy with her father. She is a relation of
                        yours, Princess Mary Bolkonskaya."
                                        
                        Prince Vasili did not reply, though, with the quickness of memory
                        and perception befitting a man of the world, he indicated by a
                        movement of the head that he was considering this information.
                                        
                        "Do you know," he said at last, evidently unable to check the sad
                        current of his thoughts, "that Anatole is costing me forty thousand
                        rubles a year? And," he went on after a pause, "what will it be in
                        five years, if he goes on like this?" Presently he added: "That's what
                        we fathers have to put up with.... Is this princess of yours rich?"
                                        
                        "Her father is very rich and stingy. He lives in the country. He
                        is the well-known Prince Bolkonski who had to retire from the army
                        under the late Emperor, and was nicknamed 'the King of Prussia.' He is
                        very clever but eccentric, and a bore. The poor girl is very
                        unhappy. She has a brother; I think you know him, he married Lise
                        Meinen lately. He is an aide-de-camp of Kutuzov's and will be here
                        tonight."
                                        
                        "Listen, dear Annette," said the prince, suddenly taking Anna
                        Pavlovna's hand and for some reason drawing it downwards. "Arrange
                        that affair for me and I shall always be your most devoted slave-
                        slafe with an f, as a village elder of mine writes in his reports.
                        She is rich and of good family and that's all I want."
                                        
                        And with the familiarity and easy grace peculiar to him, he raised
                        the maid of honor's hand to his lips, kissed it, and swung it to and
                        fro as he lay back in his armchair, looking in another direction.
                                        
                        "Attendez," said Anna Pavlovna, reflecting, "I'll speak to Lise,
                        young Bolkonski's wife, this very evening, and perhaps the thing can
                        be arranged. It shall be on your family's behalf that I'll start my
                        apprenticeship as old maid."
                        """
                        .toLowerCase()
                        .replaceAll("\\p{Punct}+", "")
                        .split("\\s+")
        );
        double peaceDensity = calculateDensity(wordList, peaceTerms);
        double warDensity = calculateDensity(wordList, warTerms);
        Chapter chapter = new Chapter(1, peaceDensity, warDensity);
        assertEquals("Chapter 1: peace-related", chapter.toString());
    }

    @Test
    void calculateDensityTest_Chapter10() {
        List<String> wordList = List.of(
                """
                        Prince Vasili kept the promise he had given to Princess
                        Drubetskaya who had spoken to him on behalf of her only son Boris on
                        the evening of Anna Pavlovna's soiree. The matter was mentioned to the
                        Emperor, an exception made, and Boris transferred into the regiment of
                        Semenov Guards with the rank of cornet. He received, however, no
                        appointment to Kutuzov's staff despite all Anna Mikhaylovna's
                        endeavors and entreaties. Soon after Anna Pavlovna's reception Anna
                        Mikhaylovna returned to Moscow and went straight to her rich
                        relations, the Rostovs, with whom she stayed when in the town and
                        where her darling Bory, who had only just entered a regiment
                        of the line and was being at once transferred to the Guards as a
                        cornet, had been educated from childhood and lived for years at a
                        time. The Guards had already left Petersburg on the tenth of August,
                        and her son, who had remained in Moscow for his equipment, was to join
                        them on the march to Radzivilov.
                                                
                        It was St. Natalia's day and the name day of two of the Rostovs--the
                        mother and the youngest daughter--both named Nataly. Ever since the
                        morning, carriages with six horses had been coming and going
                        continually, bringing visitors to the Countess Rostova's big house
                        on the Povarskaya, so well known to all Moscow. The countess herself
                        and her handsome eldest daughter were in the drawing-room with the
                        visitors who came to congratulate, and who constantly succeeded one
                        another in relays.
                                                
                        The countess was a woman of about forty-five, with a thin Oriental
                        type of face, evidently worn out with childbearing--she had had
                        twelve. A languor of motion and speech, resulting from weakness,
                        gave her a distinguished air which inspired respect. Princess Anna
                        Mikhaylovna Drubetskaya, who as a member of the household was also
                        seated in the drawing room, helped to receive and entertain the
                        visitors. The young people were in one of the inner rooms, not
                        considering it necessary to take part in receiving the visitors. The
                        count met the guests and saw them off, inviting them all to dinner.
                                                
                        "I am very, very grateful to you, mon cher," or "ma chere"--he
                        called everyone without exception and without the slightest
                        variation in his tone, "my dear," whether they were above or below him
                        in rank--"I thank you for myself and for our two dear ones whose
                        name day we are keeping. But mind you come to dinner or I shall be
                        offended, ma chere! On behalf of the whole family I beg you to come,
                        mon cher!" These words he repeated to everyone without exception or
                        variation, and with the same expression on his full, cheerful,
                        clean-shaven face, the same firm pressure of the hand and the same
                        quick, repeated bows. As soon as he had seen a visitor off he returned
                        to one of those who were still in the drawing room, drew a chair
                        toward him or her, and jauntily spreading out his legs and putting his
                        hands on his knees with the air of a man who enjoys life and knows how
                        to live, he swayed to and fro with dignity, offered surmises about the
                        weather, or touched on questions of health, sometimes in Russian and
                        sometimes in very bad but self-confident French; then again, like a
                        man weary but unflinching in the fulfillment of duty, he rose to see
                        some visitors off and, stroking his scanty gray hairs over his bald
                        patch, also asked them to dinner. Sometimes on his way back from the
                        anteroom he would pass through the conservatory and pantry into the
                        large marble dining hall, where tables were being set out for eighty
                        people; and looking at the footmen, who were bringing in silver and
                        china, moving tables, and unfolding damask table linen, he would
                        call Dmitri Vasilevich, a man of good family and the manager of all
                        his affairs, and while looking with pleasure at the enormous table
                        would say: "Well, Dmitri, you'll see that things are all as they
                        should be? That's right! The great thing is the serving, that's it."
                        And with a complacent sigh he would return to the drawing room.
                                                
                        "Marya Lvovna Karagina and her daughter!" announced the countess'
                        gigantic footman in his bass voice, entering the drawing room. The
                        countess reflected a moment and took a pinch from a gold snuffbox with
                        her husband's portrait on it.
                                                
                        "I'm quite worn out by these callers. However, I'll see her and no
                        more. She is so affected. Ask her in," she said to the footman in a
                        sad voice, as if saying: "Very well, finish me off."
                                                
                        A tall, stout, and proud-looking woman, with a round-faced smiling
                        daughter, entered the drawing room, their dresses rustling.
                                                
                        "Dear Countess, what an age... She has been laid up, poor child...
                        at the Razumovski's ball... and Countess Apraksina... I was so
                        delighted..." came the sounds of animated feminine voices,
                        interrupting one another and mingling with the rustling of dresses and
                        the scraping of chairs. Then one of those conversations began which
                        last out until, at the first pause, the guests rise with a rustle of
                        dresses and say, "I am so delighted... Mamma's health... and
                        Countess Apraksina..." and then, again rustling, pass into the
                        anteroom, put on cloaks or mantles, and drive away. The conversation
                        was on the chief topic of the day: the illness of the wealthy and
                        celebrated beau of Catherine's day, Count Bezukhov, and about his
                        illegitimate son Pierre, the one who had behaved so improperly at Anna
                        Pavlovna's reception.
                                                
                        "I am so sorry for the poor count," said the visitor. "He is in such
                        bad health, and now this vexation about his son is enough to kill
                        him!"
                                                
                        "What is that?" asked the countess as if she did not know what the
                        visitor alluded to, though she had already heard about the cause of
                        Count Bezukhov's distress some fifteen times.
                                                
                        "That's what comes of a modern education," exclaimed the visitor.
                        "It seems that while he was abroad this young man was allowed to do as
                        he liked, now in Petersburg I hear he has been doing such terrible
                        things that he has been expelled by the police."
                                                
                        "You don't say so!" replied the countess.
                                                
                        "He chose his friends badly," interposed Anna Mikhaylovna. "Prince
                        Vasili's son, he, and a certain Dolokhov have, it is said, been up
                        to heaven only knows what! And they have had to suffer for it.
                        Dolokhov has been degraded to the ranks and Bezukhov's son sent back
                        to Moscow. Anatole Kuragin's father managed somehow to get his son's
                        affair hushed up, but even he was ordered out of Petersburg."
                                                
                        "But what have they been up to?" asked the countess.
                                                
                        "They are regular brigands, especially Dolokhov," replied the
                        visitor. "He is a son of Marya Ivanovna Dolokhova, such a worthy
                        woman, but there, just fancy! Those three got hold of a bear
                        somewhere, put it in a carriage, and set off with it to visit some
                        actresses! The police tried to interfere, and what did the young men
                        do? They tied a policeman and the bear back to back and put the bear
                        into the Moyka Canal. And there was the bear swimming about with the
                        policeman on his back!"
                                                
                        "What a nice figure the policeman must have cut, my dear!" shouted
                        the count, dying with laughter.
                                                
                        "Oh, how dreadful! How can you laugh at it, Count?"
                                                
                        Yet the ladies themselves could not help laughing.
                                                
                        "It was all they could do to rescue the poor man," continued the
                        visitor. "And to think it is Cyril Vladimirovich Bezukhov's son who
                        amuses himself in this sensible manner! And he was said to be so
                        well educated and clever. This is all that his foreign education has
                        done for him! I hope that here in Moscow no one will receive him, in
                        spite of his money. They wanted to introduce him to me, but I quite
                        declined: I have my daughters to consider."
                                                
                        "Why do you say this young man is so rich?" asked the countess,
                        turning away from the girls, who at once assumed an air of
                        inattention. "His children are all illegitimate. I think Pierre also
                        is illegitimate."
                                                
                        The visitor made a gesture with her hand.
                                                
                        "I should think he has a score of them."
                                                
                        Princess Anna Mikhaylovna intervened in the conversation,
                        evidently wishing to show her connections and knowledge of what went
                        on in society.
                                                
                        "The fact of the matter is," said she significantly, and also in a
                        half whisper, "everyone knows Count Cyril's reputation.... He has lost
                        count of his children, but this Pierre was his favorite."
                                                
                        "How handsome the old man still was only a year ago!" remarked the
                        countess. "I have never seen a handsomer man."
                                                
                        "He is very much altered now," said Anna Mikhaylovna. "Well, as I
                        was saying, Prince Vasili is the next heir through his wife, but the
                        count is very fond of Pierre, looked after his education, and wrote to
                        the Emperor about him; so that in the case of his death--and he is
                        so ill that he may die at any moment, and Dr. Lorrain has come from
                        Petersburg--no one knows who will inherit his immense fortune,
                        Pierre or Prince Vasili. Forty thousand serfs and millions of
                        rubles! I know it all very well for Prince Vasili told me himself.
                        Besides, Cyril Vladimirovich is my mother's second cousin. He's also
                        my Bory's godfather," she added, as if she attached no importance at
                        all to the fact.
                                                
                        "Prince Vasili arrived in Moscow yesterday. I hear he has come on
                        some inspection business," remarked the visitor.
                                                
                        "Yes, but between ourselves," said the princess, "that is a
                        pretext. The fact is he has come to see Count Cyril Vladimirovich,
                        hearing how ill he is."
                                                
                        "But do you know, my dear, that was a capital joke," said the count;
                        and seeing that the elder visitor was not listening, he turned to
                        the young ladies. "I can just imagine what a funny figure that
                        policeman cut!"
                                                
                        And as he waved his arms to impersonate the policeman, his portly
                        form again shook with a deep ringing laugh, the laugh of one who
                        always eats well and, in particular, drinks well. "So do come and dine
                        with us!" he said.
                        """
                        .toLowerCase()
                        .replaceAll("\\p{Punct}+", "")
                        .split("\\s+")
        );
        double peaceDensity = calculateDensity(wordList, peaceTerms);
        double warDensity = calculateDensity(wordList, warTerms);
        Chapter chapter = new Chapter(10, peaceDensity, warDensity);
        assertEquals("Chapter 10: war-related", chapter.toString());
    }
}
