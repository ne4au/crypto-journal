import Header from './Header';
import styles from '../../styles';

function Home() {
    return (
        <div className="bg-primary w-full overflow-hidden">
            <div className={`${styles.paddingX} ${styles.flexCenter}`}>
                <div className={`${styles.boxWidth}`}>
                    <Header />
                </div>
            </div>

            <div className={`bg-primary ${styles.flexStart}`}>
            </div>
        </div >
    )
}

export default Home;