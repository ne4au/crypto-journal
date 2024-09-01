import { FC } from 'react';
import GetStarted from './GetStarted';
import styles from '../../styles'
import { robotHand, chart } from '../../assets';
import SigningType from '../sign/SigningType';

interface Props {
    signingOpenListener: (signingType: SigningType) => void,
}

const MainHome: FC<Props> = ({
    signingOpenListener,
}) => {
    return (
        <section id="home" className={`flex md:flex-row flex-col sm:py-8 py-2`}>
            <div className={`flex-1 ${styles.flexStart} flex-col sm:px-16 px-6`}>
                <div className="flex flex-row justify-between items-center w-full">
                    <h1 className="flex-1 font-poppins font-semibold ss:text-[72px] text-[52px] text-white ss:leading-[100.8px] leading-[75px]">
                        The Next <br className="sm:block hidden" />{" "}
                        <span className="text-gradient">Generation</span>{" "}
                    </h1>
                    <div className="ss:flex hidden md:mr-4 mr-0">
                        <GetStarted
                            signingOpenListener={signingOpenListener} />
                    </div>
                </div>


                <h1 className="font-poppins font-semibold ss:text-[68px] text-[52px] text-white ss:leading-[100.8px] leading-[75px] w-full">
                    Of Crypto Trading.
                </h1>
                <p className={`${styles.paragraph} max-w-[470px] mt-5`}>
                    Our expert team crafts tailored analytics designed to meet your unique needs.
                    We meticulously analyze every report and explore every option 
                    to ensure you have the best possible approach.
                </p>
            </div>

            <div className={`flex-1 flex ${styles.flexCenter} md:my-0 my-5 relative`}>

                <div className='flex-1'>
                    <img src={chart} alt="billing" className="chart-image w-[100%] h-[100%] relative z-[5]" />
                    <img src={robotHand} alt="billing" className="w-[100%] h-[100%] relative z-[5]" />
                </div>
                {/* gradient start */}
                <div className="absolute z-[0] w-[40%] h-[35%] top-0 pink__gradient" />
                <div className="absolute z-[1] w-[80%] h-[80%] rounded-full white__gradient bottom-40" />
                <div className="absolute z-[0] w-[50%] h-[50%] right-20 bottom-20 blue__gradient" />
                {/* gradient end */}
            </div>

            <div className={`ss:hidden ${styles.flexCenter}`}>
                <GetStarted
                    signingOpenListener={signingOpenListener} />
            </div>


        </section >
    );
}

export default MainHome;
