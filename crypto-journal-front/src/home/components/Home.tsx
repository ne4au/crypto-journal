import Header from './Header';
import MainHome from './MainHome';
import Stats from './Stats';
import styles from '../../styles';
import Motivation from './Motivation';

function Home() {
    return (
        <div className="bg-primary w-full overflow-hidden">
            <div className={` ${styles.flexCenter}`}>
                <div className="w-full px-5">
                    <Header />
                </div>
            </div>

            <div className={`bg-primary ${styles.flexStart}`}>
                <div className={`${styles.boxWidth}`}>
                    <MainHome />
                </div>
            </div>

            <div className={`bg-primary ${styles.flexStart}`}>
                <div className={`${styles.boxWidth}`}>
                    <Stats />
                    <Motivation/>
                </div>
            </div>
        </div >
    )
}

export default Home;