import { FC } from 'react';
import FeatureCard from './FeatureCard';
import HomeButton from './HomeButton';
import styles from '../../styles';
import { layout } from '../../styles';
import FeatureList from './FeaturesList';

const Motivation : FC = () =>  (
    <section id="features" className={layout.section}>
    <div className={layout.sectionInfo}>
      <h2 className={styles.heading2}>
        You do the trading, <br className="sm:block hidden" /> the profit grows.
      </h2>
      <p className={`${styles.paragraph} max-w-[470px] mt-5`}>
        With the right strategy, you can improve your financial life by
        trading, earning rewards and saving money. 
      </p>

      <HomeButton styles={`mt-10`} />
    </div>

    <FeatureList/>
  </section>
);

export default Motivation;