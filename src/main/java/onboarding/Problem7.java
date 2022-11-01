package onboarding;

import java.util.*;

public class Problem7 {
    public static List<String> solution(String user, List<List<String>> friends, List<String> visitors) {
        List<String> answer = Collections.emptyList();
        //최대 5명까지 점수 높은 순으로 정렬하여 리턴
        //점수가 0이면 추천하지 않음
        //점수가 같으면 이름 순으로 정렬
        List<String> userFriends = getUserFriends(user, friends);

        //유저의 친구를 제외한 나머지 친구 맵 만들기
        Map<String, List<String>> unknownFriends = getUnknownFriends(user, friends, userFriends);

        //나머지 친구 맵에 밸류로 친구 등록
        registerFriendInUnknownFriends(friends, unknownFriends);

        return answer;
    }

    public static List<String> getUserFriends(String user, List<List<String>> friends) {
        List<String> userFriends = new ArrayList<>();

        for (List<String> relation : friends) {

            if (relation.get(0).equals(user)) {
                userFriends.add(relation.get(1));
            }

            if (relation.get(1).equals(user)) {
                userFriends.add((relation.get(0)));
            }

        }

        return userFriends;
    }

    public static Map<String, List<String>> getUnknownFriends(String user,
                                                              List<List<String>> friends,
                                                              List<String> userFriends) {
        Map<String, List<String>> unknownFriends = new HashMap<>();

        for (List<String> friend : friends) {
            for (int j = 0; j < 2; j++) {

                if (friend.get(j).equals(user) || userFriends.contains(friend.get(j))) {
                    continue;
                }

                if (!unknownFriends.containsKey(friend.get(j))) {
                    List<String> friendList = new ArrayList<>();
                    unknownFriends.put(friend.get(j), friendList);
                }
            }
        }

        return unknownFriends;
    }

    public static void registerFriendInUnknownFriends(List<List<String>> friends,
                                                      Map<String, List<String>> unknownFriends) {

        for (List<String> friend : friends) {

            if (unknownFriends.containsKey(friend.get(0))) {
                unknownFriends.get(friend.get(0)).add(friend.get(1));
            }

            if (unknownFriends.containsKey(friend.get(1))) {
                unknownFriends.get(friend.get(1)).add(friend.get(0));
            }

        }
    }
}
