package org.useractivitylog;

import com.datastax.oss.driver.api.core.cql.Row;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
public class Main {
    public static void main(String[] args) {
        ActivityLogService logger = new ActivityLogService("127.0.0.1", "user_activity_keyspace");
        UUID userId = UUID.randomUUID();
        Instant baseTime = Instant.now();

        Object[][] activities = {
                {"add_to_cart", -5L},
                {"view_product", -15L},
                {"apply_discount", -20L},
                {"proceed_to_checkout", -25L},
                {"enter_shipping_address", -30L},
                {"select_payment_method", -35L},
                {"place_order", -40L},
                {"track_order", -45L},
                {"cancel_order", -50L},
                {"leave_review", -60L},
                {"add_to_wishlist", -70L},
                {"refer_friend", -80L},
                {"contact_support", -90L},
                {"subscribe_newsletter", -100L},
               
        };



        for (Object[] activity : activities) {
            long offset = ((Number) activity[1]).longValue();
            Instant timestamp = baseTime.plusSeconds(offset);
            logger.logUserActivity(
                    userId,
                    (String) activity[0],
                    timestamp,
                    30
            );
        }
       UUID anotherUserId = UUID.randomUUID();
        logger.logUserActivity(anotherUserId, "login", baseTime.minusSeconds(600), 30);
        logger.logUserActivity(anotherUserId, "view_dashboard", baseTime.minusSeconds(590), 30);
       System.out.println("\nRecent activities for main user:");
        List<Row> recentActivities = logger.getRecentUserActivities(userId, 5);
        for (Row row : recentActivities) {
            System.out.printf("user_id: %s, activity_id: %s, activity_timestamp: %s, activity_type: %s%n",
                    row.getUuid("user_id"),
                    row.getUuid("activity_id"),
                    row.getInstant("activity_timestamp"),
                    row.getString("activity_type"));
        }
       System.out.println("\nRecent activities for another user:");
        List<Row> anotherUserActivities = logger.getRecentUserActivities(anotherUserId, 5);
        for (Row row : anotherUserActivities) {
            System.out.printf("user_id: %s, activity_id: %s, activity_timestamp: %s, activity_type: %s%n",
                    row.getUuid("user_id"),
                    row.getUuid("activity_id"),
                    row.getInstant("activity_timestamp"),
                    row.getString("activity_type"));
        }
        Instant startTime = baseTime.minusSeconds(300);
        Instant endTime = baseTime.plusSeconds(60);
        System.out.println("\nActivities for main user within time range:");
        List<Row> activitiesInRange = logger.getUserActivitiesWithinRange(userId, startTime, endTime);
        for (Row row : activitiesInRange) {
            System.out.printf("user_id: %s, activity_id: %s, activity_timestamp: %s, activity_type: %s%n",
                    row.getUuid("user_id"),
                    row.getUuid("activity_id"),
                    row.getInstant("activity_timestamp"),
                    row.getString("activity_type"));
        }


        logger.close();
    }
}
