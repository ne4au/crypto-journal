import { FC } from 'react';
import styles from '../../styles';
import HomeButton from './HomeButton';

const CTA: FC = () => (
  <section className={`${styles.flexCenter} ${styles.marginY} ${styles.padding} sm:flex-row flex-col bg-black-gradient-2 rounded-[20px] box-shadow`}>
    <div className="flex-1 flex flex-col">
      <h2 className={styles.heading2}>Let’s try our service now!</h2>
      <p className={`${styles.paragraph} max-w-[470px] mt-5`}>
        Start trading with us today and grow your wealth from anywhere in the world.
      </p>
    </div>

    <div className={`${styles.flexCenter} sm:ml-10 ml-0 sm:mt-0 mt-10`}>
      <HomeButton styles='' />
    </div>
  </section>
);

export default CTA;