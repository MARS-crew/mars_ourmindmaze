//package mars.ourmindmaze.dm;
//
//import mars.ourmindmaze.common.dto.UserAuthority;
//import mars.ourmindmaze.domain.Dm;
//import mars.ourmindmaze.domain.User;
//import mars.ourmindmaze.repository.dm.DmJpaRepository;
//import mars.ourmindmaze.repository.user.UserJpaRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//
//import javax.transaction.Transactional;
//import java.util.Optional;
//
//@SpringBootTest
//public class DmServiceTestV0 {
//
//    @Autowired
//    private DmJpaRepository dmJpaRepository;
//
//    @Autowired
//    private UserJpaRepository userJpaRepository;
//
//    @DisplayName("dm 전송 테스트 - SUCCESS")
//    @Test
//    @Transactional
//    @Rollback
//    void sendDm() {
//        // given
//        User sender = userJpaRepository.save(User.builder()
//                .username("sender")
//                .password("1234")
//                .authority(UserAuthority.ROLE_USER)
//                .build());
//
//        User reciver = userJpaRepository.save(User.builder()
//                .username("reciver")
//                .password("1234")
//                .authority(UserAuthority.ROLE_USER)
//                .build());
//
//        Dm sendDm = dmJpaRepository.save(Dm.builder()
//                .sender(sender)
//                .reciver(reciver)
//                .content("Hello")
//                .build());
//
//        // when
//        Optional<Dm> findDm = dmJpaRepository.findById(sendDm.getId());
//
//        // then
//        Assertions.assertThat(sendDm).isEqualTo(findDm.get());
//    }
//}
