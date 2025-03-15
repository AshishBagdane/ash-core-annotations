/**
 *
 */
package com.ashishbagdane.lib.annotations.services;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * A composite annotation that combines Spring's {@link Service} capability with transactional behavior. This annotation is designed to be used on service layer components to enforce consistent
 * transaction management and component scanning behavior across the application.
 *
 * <p>Classes annotated with {@code @ApplicationService} will automatically:
 * <ul>
 *   <li>Be registered as Spring service components</li>
 *   <li>Have transactional behavior with READ_COMMITTED isolation</li>
 *   <li>Use REQUIRED transaction propagation</li>
 *   <li>Roll back for any Exception</li>
 * </ul>
 *
 * <p><b>Usage Example:</b>
 * <pre>
 * {@code
 * @ApplicationService
 * public class UserService {
 *     // Service implementation
 * }
 * }
 * </pre>
 *
 * <p><b>With Custom Bean Name:</b>
 * <pre>
 * {@code
 * @ApplicationService("customUserService")
 * public class UserService {
 *     // Service implementation
 * }
 * }
 * </pre>
 *
 * <p><b>Transaction Management:</b>
 * <ul>
 *   <li>Isolation Level: READ_COMMITTED - Prevents dirty reads while allowing non-repeatable reads and phantom reads</li>
 *   <li>Propagation: REQUIRED - Ensures a transaction is always present, creating new or joining existing</li>
 *   <li>Exception Handling: Rolls back for any Exception or its subclasses</li>
 * </ul>
 *
 * <p><b>Important Notes:</b>
 * <ul>
 *   <li>This annotation is inherited by subclasses</li>
 *   <li>Should only be used on service layer components</li>
 *   <li>Requires a Spring context to function</li>
 * </ul>
 *
 * @author YourName
 * @see Service
 * @see Transactional
 * @see Inherited
 * @since 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
@Transactional(
    propagation = Propagation.REQUIRED,
    isolation = Isolation.READ_COMMITTED,
    rollbackFor = Exception.class
)
@Inherited
public @interface ApplicationService {

  /**
   * Suggests a logical component name for the annotated class, which Spring will use when creating the service bean. If not specified, Spring will generate a default bean name based on the class
   * name.
   *
   * <p>This name will be used to identify the bean in the Spring context and can be
   * referenced when using {@code @Qualifier} or similar annotations for dependency injection.
   *
   * <p><b>Example:</b>
   * <pre>
   * {@code
   * @ApplicationService("internationalOrderService")
   * public class InternationalOrderService {
   *     // Service implementation
   * }
   * }
   * </pre>
   *
   * @return the suggested component name, if any (defaults to empty string)
   * @see Service#value()
   */
  String value() default "";
}
