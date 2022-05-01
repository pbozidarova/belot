package belot.model.gamemodel;

import belot.model.cardmodel.Card;
import belot.model.cardmodel.enums.RankEnum;
import belot.model.cardmodel.enums.SuitEnum;
import belot.model.gamemodel.enums.DeclarationEnum;

import java.util.*;
import java.util.stream.Collectors;

public class Declarations  {

    private Map<Integer, Map<Integer, Integer>> declarationsBySuit;
    List<Card> cardsToAnalyse;

    public Declarations()  {
        cardsToAnalyse = new ArrayList<>();
    }

    public int getDeclarationsScore() {
        int[] score = new int[1];

        declarationsBySuit.forEach((suit, map) ->  {
            map.forEach((points, times) ->  {
                score[0] += points * times;
            });
        });

        return score[0];
    }

    public String scanForDeclarations(List<Card> sortedCards)  {
        this.cardsToAnalyse = sortedCards;

        String declarations = "";
        this.declarationsBySuit = new HashMap<>();

        Arrays.stream(SuitEnum.values()).forEach(suit ->  {
            this.declarationsBySuit.put(suit.getCode(), new HashMap<>());
        });

        declarations += scanForConsecutiveCards(); //TIERCE, QUARTE, QUINTE
        declarations += scanForCarre(); //CARRE

        return declarations;
    }

    private boolean cardsAreInSequence(List<Card> cards) {
        boolean areInSequence = true;

        for (int i = 0; i < cards.size() - 1; i++)  {
            if (cards.get(i).getRank().getCode() + 1 != cards.get(i + 1).getRank().getCode()) {
                areInSequence = false;
                break;
            }
        }

        return areInSequence;
    }

    private String scanForConsecutiveCards() {
        String[] sequence = new String[] {""};
        Arrays.stream(SuitEnum.values()).forEach(suit ->  {
            int suitCode = suit.getCode();
            List<Card> cardsWithTheSameSuit = extractCardsFromTheSameSuit(suitCode);

            List<Card> firstSubListFromTheSameSuit = new ArrayList<>();
            List<Card> secondSubListFromTheSameSuit = new ArrayList<>();

            if (cardsWithTheSameSuit.size() >= 5) {
                firstSubListFromTheSameSuit = cardsWithTheSameSuit.subList(0, 5);
                secondSubListFromTheSameSuit = cardsWithTheSameSuit.subList(5, cardsWithTheSameSuit.size());

                int firstSublistDeclarationsCode = findTheDeclarationCode(firstSubListFromTheSameSuit);
                int secondSublistDeclarationsCode = findTheDeclarationCode(secondSubListFromTheSameSuit);

                if (firstSublistDeclarationsCode != 0) {
                    sequence[0] += markSequence(suitCode, firstSublistDeclarationsCode);
                }

                if (secondSublistDeclarationsCode != 0) {
                    sequence[0] += markSequence(suitCode, secondSublistDeclarationsCode);
                }

            } else  {
                int declarationCode = findTheDeclarationCode(cardsWithTheSameSuit);

                if (declarationCode != 0) {
                    sequence[0] += markSequence(suitCode, declarationCode);
                }
            }
        });

        return sequence[0];
    }

    private String markSequence(int suitCode, int declarationCode)  {
        String announcementString = "";

        switch (declarationCode) {
            case 3:
                int tierceCode = DeclarationEnum.TIERCE.getCode();
                announcementString = markAnnouncement(suitCode, tierceCode);
                break;
            case 4:
                int quarteCode = DeclarationEnum.QUARTE.getCode();
                announcementString = markAnnouncement(suitCode, quarteCode);
                break;
            case 5:
                int quinteCode = DeclarationEnum.QUINTE.getCode();
                announcementString = markAnnouncement(suitCode, quinteCode);
                break;
            default:
                break;
        }

        return announcementString;
    }

    private String markAnnouncement(int suitCode, int announcementCode) {
        this.declarationsBySuit.get(suitCode).putIfAbsent(announcementCode, 0);
        int countOfQuinte = declarationsBySuit.get(suitCode).get(announcementCode) + 1;
        this.declarationsBySuit.get(suitCode).put(announcementCode, countOfQuinte).toString();

        return String.format("%s - %s ", DeclarationEnum.valueOf(announcementCode), SuitEnum.valueOf(suitCode));
    }

    private int findTheDeclarationCode(List<Card> cardsWithTheSameSuit)  {
        int declarationCode = 0;

        if (cardsWithTheSameSuit.size() >= 3) {
            for (int i = 3; i <= cardsWithTheSameSuit.size(); i++)  {
                List<Card> extractCards = cardsToAnalyse.subList(0, i);
                if (cardsAreInSequence(extractCards)) {
                    declarationCode = i;
                }
            }
        }

        return declarationCode;
    }

    private List<Card> extractCardsFromTheSameSuit(int suit) {
        List<Card> cardsFromTheSameSuit = new ArrayList<>();

        for (int i = 0; i < cardsToAnalyse.size(); i++)  {
            Card card = cardsToAnalyse.get(i);
            if (card.getSuit().getCode() == suit) {
                cardsFromTheSameSuit.add(card);
            }
        }

        return cardsFromTheSameSuit;
    }


    private String scanForCarre() {
        String carreString = "";

        for (int i = 0; i < this.cardsToAnalyse.size() - 3; i++)  {
            RankEnum currentCardRank = this.cardsToAnalyse.get(i).getRank();
            boolean hasCarre = false;

            List<Integer> nextThreeCardsRankCode = this.cardsToAnalyse.subList(i + 1, i + 3)
                    .stream()
                    .map(card -> card.getRank().getCode())
                    .collect(Collectors.toList());

            if (nextThreeCardsRankCode.contains(currentCardRank.getCode())) {
                int occurrences = Collections.frequency(nextThreeCardsRankCode, currentCardRank.getCode());
                hasCarre = occurrences == nextThreeCardsRankCode.size();
            }

            if (hasCarre) {
                carreString = markCarre(currentCardRank);
            }
        }

        return carreString;
    }

    private String markCarre(RankEnum rank)  {
        String announcementString = "";
        switch (rank) {
            case TEN:
            case QUEEN:
            case KING:
            case ACE:
                announcementString = markAnnouncement(0, DeclarationEnum.CARRE.getCode());
                break;
            case NINE:
                announcementString = markAnnouncement(0, DeclarationEnum.CARRE9.getCode());
                break;
            case JACK:
                announcementString = markAnnouncement(0, DeclarationEnum.CARREJ.getCode());
                break;
            default:
                break;
        }

        return announcementString;
    }


}
