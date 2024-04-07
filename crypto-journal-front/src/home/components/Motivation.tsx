import FeatureCard from './FeatureCard';
import HomeButton from './HomeButton';
import styles from '../../styles';
import { layout } from '../../styles';
import FeatureList from './FeaturesList';

const Motivation = () =>  (
    <section id="features" className={layout.section}>
    <div className={layout.sectionInfo}>
      <h2 className={styles.heading2}>
        You do the business, <br className="sm:block hidden" /> we’ll handle
        the money.
      </h2>
      <p className={`${styles.paragraph} max-w-[470px] mt-5`}>
        With the right credit card, you can improve your financial life by
        building credit, earning rewards and saving money. But with hundreds
        of credit cards on the market.
      </p>

      <HomeButton styles={`mt-10`} />
    </div>

    <FeatureList/>
  </section>
);

export default Motivation;